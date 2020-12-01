package org.colin.controller.yw;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.colin.model.ro.BbsPost;
import org.colin.model.ro.BbsReply;
import org.colin.model.vo.resp.*;
import org.colin.service.*;
import org.colin.utils.*;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: H5页面获取信息(无须登录)
 * @ClassName: BbsController
 * @Author: wujiangbo
 * @Date: 2020/6/22 0022 14:43
 * @Version: 1.1.0
 */
@RestController
@Api(tags = "北方社区接口")
@RequestMapping("/h5")
@Slf4j
@EnableAsync
public class H5Controller {

    @Resource
    private BbsPostService bbsPostService;

    private String user_id = "fcf34b56-a7a2-4719-9236-867495e74c31";//审核人员ID

    @GetMapping("/reply/list/{replyState}")
    @ApiOperation(value = "根据评论状态查询所有评论信息")
    public DataResult<List<ReplyVO>> replyList(@PathVariable("replyState") String replyState) {
        DataResult<List<ReplyVO>> result = DataResult.success();
        result.setData(bbsPostService.selectAllReplyByState(replyState));
        return result;
    }

    @GetMapping("/post/list/{postState}")
    @ApiOperation(value = "根据帖子状态查询所有帖子信息")
    public DataResult<List<BbsPostVO>> postList(@PathVariable("postState") String postState) {
        DataResult<List<BbsPostVO>> result = DataResult.success();
        result.setData(bbsPostService.selectAllPostByState(postState));
        return result;
    }

    @GetMapping("/post/getById/{id}")
    @ApiOperation(value = "查询帖子详情")
    public DataResult<BbsPostVO> getPostById(@PathVariable("id") String id) {
        DataResult<BbsPostVO> result = DataResult.success();
        result.setData(bbsPostService.selectPostDetail(id));
        return result;
    }

    @GetMapping("/reply/getById/{id}")
    @ApiOperation(value = "查询评论详情")
    public DataResult<ReplyVO> getReplyById(@PathVariable("id") String id) {
        DataResult<ReplyVO> result = DataResult.success();
        result.setData(bbsPostService.getReplyById(id));
        return result;
    }

    @DeleteMapping("/deletePost/{id}")
    @ApiOperation(value = "删除帖子信息")
    public DataResult deletePost(@PathVariable("id") String id) {
        bbsPostService.deleteByPkId(id);
        return DataResult.success();
    }

    @DeleteMapping("/deleteReply/{id}")
    @ApiOperation(value = "删除评论信息")
    public DataResult deleteReply(@PathVariable("id") String id) {
        bbsPostService.deleteReplyByPkId(id);
        return DataResult.success();
    }

    @PutMapping("/post/updatePost")
    @ApiOperation(value = "更新帖子信息")
    public DataResult updatePost(@RequestBody BbsPost post) {
        post.setUpdateUserId(user_id);
        bbsPostService.updatePostById(post);
        return DataResult.success();
    }

    @PutMapping("/post/updateReply")
    @ApiOperation(value = "更新评论信息")
    public DataResult updateReply(@RequestBody BbsReply obj) {
        obj.setUpdateUserId(user_id);
        bbsPostService.updateReplyById(obj);
        return DataResult.success();
    }
}
