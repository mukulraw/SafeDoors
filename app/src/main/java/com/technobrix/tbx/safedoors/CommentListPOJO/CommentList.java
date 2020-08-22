package com.technobrix.tbx.safedoors.CommentListPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tvs on 10/24/2017.
 */

public class CommentList {

    @SerializedName("comment_id")
    @Expose
    private String commentId;
    @SerializedName("topic_id")
    @Expose
    private String topicId;
    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("profile_pic")
    @Expose
    private String profilePic;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("created_time")
    @Expose
    private String createdTime;

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

}
