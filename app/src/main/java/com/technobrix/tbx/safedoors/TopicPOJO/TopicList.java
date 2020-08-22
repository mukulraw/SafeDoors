package com.technobrix.tbx.safedoors.TopicPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tvs on 10/24/2017.
 */

public class TopicList {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("profile_pic")
    @Expose
    private String profilePic;
    @SerializedName("house_id")
    @Expose
    private String houseId;
    @SerializedName("house_no")
    @Expose
    private String houseNo;
    @SerializedName("view_count")
    @Expose
    private String viewCount;
    @SerializedName("comment_count")
    @Expose
    private String commentCount;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("created_time")
    @Expose
    private String createdTime;
    @SerializedName("view_status")
    @Expose
    private Integer viewStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getViewCount() {
        return viewCount;
    }

    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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

    public Integer getViewStatus() {
        return viewStatus;
    }

    public void setViewStatus(Integer viewStatus) {
        this.viewStatus = viewStatus;
    }


}
