package org.colin.model.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
/**
 * @Description:
 * @ClassName: BbsPostReplyVOResp
 * @Author: wujiangbo
 * @Date: 2020/6/29 0029 10:44
 * @Version: 1.1.0
 */
public class BbsPostReplyVOResp implements Serializable {

    @ApiModelProperty(value = "评论信息集合")
    List<BbsPostReplyVO> postReplyVOList;

    @ApiModelProperty(value = "帖子信息")
    BbsPostVO post;

    @Override
    public String toString() {
        return "BbsPostReplyVOResp{" +
                "postReplyVOList=" + postReplyVOList +
                ", post=" + post +
                '}';
    }

    public List<BbsPostReplyVO> getPostReplyVOList() {
        return postReplyVOList;
    }

    public void setPostReplyVOList(List<BbsPostReplyVO> postReplyVOList) {
        this.postReplyVOList = postReplyVOList;
    }

    public BbsPostVO getPost() {
        return post;
    }

    public void setPost(BbsPostVO post) {
        this.post = post;
    }
}
