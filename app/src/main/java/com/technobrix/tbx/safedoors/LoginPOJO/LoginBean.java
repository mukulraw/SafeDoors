package com.technobrix.tbx.safedoors.LoginPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginBean {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("socity_id")
    @Expose
    private String socityId;
    @SerializedName("house_id")
    @Expose
    private String houseId;
    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("socity_name")
    @Expose
    private String socityName;
    @SerializedName("house_no")
    @Expose
    private String houseNo;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("category_count")
    @Expose
    private Integer categoryCount;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSocityId() {
        return socityId;
    }

    public void setSocityId(String socityId) {
        this.socityId = socityId;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSocityName() {
        return socityName;
    }

    public void setSocityName(String socityName) {
        this.socityName = socityName;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCategoryCount() {
        return categoryCount;
    }

    public void setCategoryCount(Integer categoryCount) {
        this.categoryCount = categoryCount;
    }

}
