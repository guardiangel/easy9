package org.colin.mapper.yw;

import org.apache.ibatis.annotations.Param;
import org.colin.model.entity.SysSuggestion;
import org.colin.model.entity.yw.BbsPostReply;
import org.colin.model.ro.BbsPost;
import org.colin.model.ro.BbsReply;
import org.colin.model.vo.req.BbsPostPageReqVO;
import org.colin.model.vo.req.BbsReplyPageReqVO;
import org.colin.model.vo.req.SysSuggestionPageReqVO;
import org.colin.model.vo.resp.BbsPostReplyVO;
import org.colin.model.vo.resp.BbsPostVO;
import org.colin.model.vo.resp.ReplyVO;
import org.colin.model.vo.resp.SysSuggestionVO;

import java.util.List;

public interface BbsPostMapper {

    int deleteSuggestionByPkId(String id);

    int deleteByPrimaryKey(String id);

    int deleteReplyByPrimaryKey(String id);

    //根据帖子ID删除所有评论
    public void deleteReplyByPostId(String postId);

    int insert(BbsPost record);

    int addSuggestion(SysSuggestion obj);

    int addPostReply(BbsPostReply obj);

    int insertSelective(BbsPost record);

    //查询指定帖子的评论数量
    int selectPostReplyCount(String post_id);

    BbsPostVO selectByPrimaryKey(String id);

    //更新帖子信息
    int updateByPrimaryKeySelective(BbsPost record);

    //更新评论信息
    int updateReplyByPrimaryKeySelective(BbsReply record);

    int updateByPrimaryKey(BbsPost record);

    //查询所有帖子信息(只查距离当前n个月的所有记录)
    List<BbsPostVO> selectAllPost(@Param(value = "postType") String postType, @Param(value = "month") String month);

    //根据帖子状态查询所有帖子信息
    List<BbsPostVO> selectAllPostByState(@Param(value = "postState") String postState);

    //根据评论状态查询所有评论信息
    List<ReplyVO> selectAllReplyByState(@Param(value = "replyState") String replyState);

    //分页查询所有帖子信息
    List<BbsPostVO> selectAllPostByPage(BbsPostPageReqVO vo);

    //分页查询所有评论
    List<ReplyVO> selectAllReplyByPage(BbsReplyPageReqVO vo);

    //查询评论详情
    public ReplyVO getReplyById(String replyState);

    //分页查询所有意见反馈
    List<SysSuggestionVO> selectAllSuggestionByPage(SysSuggestionPageReqVO vo);

    //查询指定帖子的所有评论信息
    List<BbsPostReplyVO> selectAllPostReply(String post_id);

    //新增点赞次数
    void addGoodCount(String id);

    //新增阅读次数
    void addReadCount(String id);

    //查询[北方社区]发帖数量
    int selectPostCount(@Param(value = "state") String state);

    //查询[北方社区]评论数量
    int selectReplyCount(@Param(value = "state") String state);
}