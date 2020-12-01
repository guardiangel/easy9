package org.colin.service.impl;

import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.colin.constants.Constant;
import org.colin.mapper.SysMessageMapper;
import org.colin.mapper.SysUserMapper;
import org.colin.mapper.yw.BbsPostMapper;
import org.colin.model.entity.SysMessage;
import org.colin.model.entity.SysSuggestion;
import org.colin.model.entity.yw.BbsPostReply;
import org.colin.model.ro.BbsPost;
import org.colin.model.ro.BbsReply;
import org.colin.model.ro.EmailRO;
import org.colin.model.vo.req.BbsPostPageReqVO;
import org.colin.model.vo.req.BbsReplyPageReqVO;
import org.colin.model.vo.req.SysSuggestionPageReqVO;
import org.colin.model.vo.resp.*;
import org.colin.service.BbsPostService;
import org.colin.service.MailService;
import org.colin.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @ClassName: BbsPostServiceImpl
 * @Author: wujiangbo
 * @Date: 2020/6/23 0023 9:25
 * @Version: 1.1.0
 */
@Service
public class BbsPostServiceImpl implements BbsPostService {

    @Autowired
    private BbsPostMapper bbsPostMapper;

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysMessageMapper messageMapper;

    @Autowired
    private RedisCheck redisCheck;

    @Override
    public void addPostGood(String post_id, String user_id) {
        redisCheck.operFrequencyCheck("addPostGood:" + user_id + "_" + post_id, user_id, 1, TimeUnit.HOURS, "1小时内只能给同一帖子点赞1次");
        bbsPostMapper.addGoodCount(post_id);
    }

    @Override
    public void addPostReply(BbsPostReply obj, String ip) {
        String pkId = Tool.getPrimaryKey();
        obj.setId(pkId);
        if(!Tool.isBlank(ip)){
            redisCheck.operFrequencyCheck("addPostReply:" + ip, pkId,3, TimeUnit.MINUTES, "3分钟内只能发一次评论");
        }else{
            redisCheck.operFrequencyCheck("addPostReply:" + pkId, pkId,3, TimeUnit.MINUTES, "3分钟内只能发一次评论");
        }
        obj.setPublishTime(new Date());
        obj.setState("1");//评论状态（0：审核未通过；1：审核中；2:审核通过）
        obj.setReplyType("0");//评论状态（0：正常；1：置顶；）
        bbsPostMapper.addPostReply(obj);
    }

    @Override
    public void addSuggestion(SysSuggestion obj) {
        //限制：3小时内只能操作一次
        redisCheck.operFrequencyCheck(Constant.USER_SUBMIT_SUGGESTION_REDIS_KEY + obj.getType() + "_" + obj.getUpdateUserId() + ":", obj.getUpdateUserId(), 3, TimeUnit.HOURS, "操作频繁！");
        obj.setId(Tool.getPrimaryKey());
        obj.setUpdateTime(new Date());
        obj.setSource("2");//来源（1：平台；2：北方社区）
        if("3".equals(obj.getType())){
            obj.setContent(obj.getCooperationDesc() + "[我的联系方式是：" + obj.getLinkStyle() + "]");
        }
        bbsPostMapper.addSuggestion(obj);
    }

    @Override
    public void addPost(BbsPost post, String ip) {
        String post_id = Tool.getPrimaryKey();
        if(!Tool.isBlank(ip)){
            redisCheck.operFrequencyCheck("addPost:" + ip + "_" + post.getPostType(), post_id,30, TimeUnit.MINUTES, "半小时内同一主题只能发一次帖");
        }else{
            redisCheck.operFrequencyCheck("addPost:" + post.getPublishUserId() + "_" + post.getPostType(), post_id,30, TimeUnit.MINUTES, "半小时内同一主题只能发一次帖");
        }
        post.setId(post_id);
        post.setPublishTime(new Date());
        post.setBbsType("0");//状态（0：正常；1：置顶；）
        post.setReadCount(0);//阅读量，默认为0
        post.setGoodCount(0);//点赞量，默认为0
        post.setState("1");//帖子状态（0：审核未通过；1：审核中；2:审核通过）
        bbsPostMapper.insertSelective(post);
    }

    @Override
    public List<BbsPostVO> selectAllPost(String postType, String month) {
        return bbsPostMapper.selectAllPost(postType, month);
    }

    @Override
    public List<BbsPostVO> selectAllPostByState(String postState) {
        return bbsPostMapper.selectAllPostByState(postState);
    }

    //根据主键ID查询帖子详情及所有评论
    @Override
    public BbsPostReplyVOResp selectPostById(String id) {
        BbsPostReplyVOResp voResp = new BbsPostReplyVOResp();
        //阅读量+1
        bbsPostMapper.addReadCount(id);
        //查询帖子详情
        BbsPostVO vo = bbsPostMapper.selectByPrimaryKey(id);
        vo.setLevel(LevelTools.getLevelByPoint(vo.getPoint()));
        vo.setLevelName(LevelTools.getLevelNameByPoint(vo.getPoint(), vo.getSex()));
        //查询帖子的总评论数量(只算审核通过的评论)
        int replyCount = bbsPostMapper.selectPostReplyCount(id);
        vo.setReplyCount(replyCount);
        //查询帖子的所有评论信息
        List<BbsPostReplyVO> replyList = bbsPostMapper.selectAllPostReply(id);
        if(replyList != null && !replyList.isEmpty()){
            for(int i=0; i<replyList.size(); i++){
                BbsPostReplyVO bbsPostReplyVO = replyList.get(i);
                bbsPostReplyVO.setLevel(LevelTools.getLevelByPoint(bbsPostReplyVO.getPoint()));
                bbsPostReplyVO.setLevelName(LevelTools.getLevelNameByPoint(bbsPostReplyVO.getPoint(), bbsPostReplyVO.getSex()));
            }
        }
        voResp.setPost(vo);
        voResp.setPostReplyVOList(replyList);
        return voResp;
    }

    @Override
    public BbsPostVO selectPostDetail(String id) {
        return bbsPostMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageVO<BbsPostVO> pageInfo(BbsPostPageReqVO vo) {
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
        List<BbsPostVO> objList = bbsPostMapper.selectAllPostByPage(vo);
        return PageUtils.getPageVO(objList);
    }

    @Override
    public PageVO<ReplyVO> replyPageInfo(BbsReplyPageReqVO vo) {
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
        List<ReplyVO> objList = bbsPostMapper.selectAllReplyByPage(vo);
        return PageUtils.getPageVO(objList);
    }

    @Override
    public List<ReplyVO> selectAllReplyByState(String replyState) {
        return bbsPostMapper.selectAllReplyByState(replyState);
    }

    @Override
    public ReplyVO getReplyById(String id) {
        return bbsPostMapper.getReplyById(id);
    }

    @Override
    public PageVO<SysSuggestionVO> suggestionPageInfo(SysSuggestionPageReqVO vo) {
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
        List<SysSuggestionVO> objList = bbsPostMapper.selectAllSuggestionByPage(vo);
        return PageUtils.getPageVO(objList);
    }

    @Override
    public void updatePost(BbsPost post) {
        post.setUpdateTime(new Date());
        String state = post.getState();
        if(!Tool.isBlank(state)){
            SysMessage obj = new SysMessage();
            obj.setId(Tool.getPrimaryKey());
            obj.setCreateUserId(post.getUpdateUserId());
            obj.setCreateTime(new Date());
            obj.setReceiveUserId(post.getPublishUserId());
            obj.setReadStatus(0);//状态（0：未读；1：已读；）
            obj.setMsgTitle("发帖审核结果通知");
            obj.setTarget("2");//消息目标（1：平台；2：北方社区）
            //帖子状态（0：审核未通过；1：审核中；2:审核通过）
            if("2".equalsIgnoreCase(state)){
                //审核通过，用户积分数增加
                if(post.getPoint() != null){
                    userMapper.updateUserPoint(post.getPublishUserId(), String.valueOf(post.getPoint()));
                }
                //审核通过，给用户发通知消息
                obj.setMsgContent("恭喜您，您发布的帖子["+post.getTitle()+"]已通过审核，积分添加"+post.getPoint() + "分");
            }
            else if("0".equalsIgnoreCase(state)){
                //审核不通过，给用户发通知消息
                obj.setMsgContent("很遗憾，您发布的帖子["+post.getTitle()+"]审核未通过，原因:[" + post.getReason() +"]");
            }
            messageMapper.insertSelective(obj);
        }
        bbsPostMapper.updateByPrimaryKeySelective(post);
    }

    @Override
    public void updatePostById(BbsPost post) {
        post.setUpdateTime(new Date());
        bbsPostMapper.updateByPrimaryKeySelective(post);
    }

    @Override
    public void updateReply(BbsReply obj) {
        obj.setUpdateTime(new Date());
        String state = obj.getState();
        if(!Tool.isBlank(state)){
            SysMessage msg = new SysMessage();
            msg.setId(Tool.getPrimaryKey());
            msg.setCreateUserId(obj.getUpdateUserId());
            msg.setCreateTime(new Date());
            msg.setReceiveUserId(obj.getPublishUserId());
            msg.setReadStatus(0);//状态（0：未读；1：已读；）
            msg.setMsgTitle("评论审核结果通知");
            msg.setTarget("2");//消息目标（1：平台；2：北方社区）
            //帖子状态（0：审核未通过；1：审核中；2:审核通过）
            if("2".equalsIgnoreCase(state)){
                //审核通过，用户积分数增加
                if(obj.getPoint() != null){
                    userMapper.updateUserPoint(obj.getPublishUserId(), String.valueOf(obj.getPoint()));
                }
                //审核通过，给用户发通知消息
                msg.setMsgContent("恭喜您，您发布的评论已通过审核，积分添加"+obj.getPoint() + "分");
            }
            else if("0".equalsIgnoreCase(state)){
                //审核不通过，给用户发通知消息
                msg.setMsgContent("很遗憾，您发布的评论["+obj.getContent()+"]审核未通过，原因:[" + obj.getReason() +"]");
            }
            messageMapper.insertSelective(msg);
        }
        bbsPostMapper.updateReplyByPrimaryKeySelective(obj);
    }

    @Override
    public void updateReplyById(BbsReply obj) {
        obj.setUpdateTime(new Date());
        bbsPostMapper.updateReplyByPrimaryKeySelective(obj);
    }

    @Override
    public void deleteByPkId(String id) {
        //删除帖子的同时，删除所有评论信息
        bbsPostMapper.deleteByPrimaryKey(id);
        bbsPostMapper.deleteReplyByPostId(id);
    }

    @Override
    public void deleteSuggestionByPkId(String id) {
        bbsPostMapper.deleteSuggestionByPkId(id);
    }

    @Override
    public void deleteReplyByPkId(String id) {
        bbsPostMapper.deleteReplyByPrimaryKey(id);
    }

}
