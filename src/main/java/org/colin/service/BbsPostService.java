package org.colin.service;

import org.colin.model.entity.SysSuggestion;
import org.colin.model.entity.yw.BbsPostReply;
import org.colin.model.ro.BbsPost;
import org.colin.model.ro.BbsReply;
import org.colin.model.vo.req.BbsPostPageReqVO;
import org.colin.model.vo.req.BbsReplyPageReqVO;
import org.colin.model.vo.req.SysSuggestionPageReqVO;
import org.colin.model.vo.resp.*;

import java.util.List;

public interface BbsPostService {

    // 给帖子点赞
    void addPostGood(String post_id, String user_id);

    // 添加帖子评论信息
    void addPostReply(BbsPostReply obj, String ip);

    // 新增意见信息
    void addSuggestion(SysSuggestion obj);

    // 新增帖子信息
    void addPost(BbsPost post, String ip);

    //查询所有帖子信息(只查距离当前n个月的所有记录)
    List<BbsPostVO> selectAllPost(String postType, String month);

    //根据帖子状态查询所有帖子信息
    List<BbsPostVO> selectAllPostByState(String postState);

    //根据主键ID查询帖子详情及所有评论
    BbsPostReplyVOResp selectPostById(String id);

    //查询帖子详情
    BbsPostVO selectPostDetail(String id);

    //分页查询帖子列表
    public PageVO<BbsPostVO> pageInfo(BbsPostPageReqVO vo);

    //分页查询评论列表
    public PageVO<ReplyVO> replyPageInfo(BbsReplyPageReqVO vo);

    //根据评论状态查询所有评论信息
    public List<ReplyVO> selectAllReplyByState(String replyState);

    //查询评论详情
    public ReplyVO getReplyById(String id);

    //分页查询所有意见反馈
    public PageVO<SysSuggestionVO> suggestionPageInfo(SysSuggestionPageReqVO vo);

    //修改帖子信息
    public void updatePost(BbsPost post);

    //更新帖子状态
    public void updatePostById(BbsPost post);

    //修改评论信息
    public void updateReply(BbsReply obj);

    //更新帖子状态
    public void updateReplyById(BbsReply obj);

    //删除帖子
    public void deleteByPkId(String id);

    //删除意见反馈
    public void deleteSuggestionByPkId(String id);

    //删除评论
    public void deleteReplyByPkId(String id);

}
