package org.colin.model.vo.resp;

public class MainVO {

    private int bbsUserCount;//[北方社区]用户总数量

    private int bbsUserCountNan;//[北方社区]男用户总数量

    private int bbsUserCountNv;//[北方社区]女用户总数量

    private int postCount;//[北方社区]发帖总数量

    private int postCountPass;//[北方社区]发帖审核通过数量

    private int postCountChecking;//[北方社区]发帖审核中数量

    private int replyCount;//[北方社区]评论总数量

    private int replyCountPass;//[北方社区]评论审核通过数量

    private int replyCountChecking;//[北方社区]评论审核中数量

    @Override
    public String toString() {
        return "MainVO{" +
                "bbsUserCount=" + bbsUserCount +
                ", bbsUserCountNan=" + bbsUserCountNan +
                ", bbsUserCountNv=" + bbsUserCountNv +
                ", postCount=" + postCount +
                ", postCountPass=" + postCountPass +
                ", postCountChecking=" + postCountChecking +
                ", replyCount=" + replyCount +
                ", replyCountPass=" + replyCountPass +
                ", replyCountChecking=" + replyCountChecking +
                '}';
    }

    public int getBbsUserCountNan() {
        return bbsUserCountNan;
    }

    public void setBbsUserCountNan(int bbsUserCountNan) {
        this.bbsUserCountNan = bbsUserCountNan;
    }

    public int getBbsUserCountNv() {
        return bbsUserCountNv;
    }

    public void setBbsUserCountNv(int bbsUserCountNv) {
        this.bbsUserCountNv = bbsUserCountNv;
    }

    public int getBbsUserCount() {
        return bbsUserCount;
    }

    public void setBbsUserCount(int bbsUserCount) {
        this.bbsUserCount = bbsUserCount;
    }

    public int getPostCount() {
        return postCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }

    public int getPostCountPass() {
        return postCountPass;
    }

    public void setPostCountPass(int postCountPass) {
        this.postCountPass = postCountPass;
    }

    public int getPostCountChecking() {
        return postCountChecking;
    }

    public void setPostCountChecking(int postCountChecking) {
        this.postCountChecking = postCountChecking;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public int getReplyCountPass() {
        return replyCountPass;
    }

    public void setReplyCountPass(int replyCountPass) {
        this.replyCountPass = replyCountPass;
    }

    public int getReplyCountChecking() {
        return replyCountChecking;
    }

    public void setReplyCountChecking(int replyCountChecking) {
        this.replyCountChecking = replyCountChecking;
    }
}
