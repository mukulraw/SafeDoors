package com.technobrix.tbx.safedoors.GetNotificationPOJO;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationList {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("socity_id")
    @Expose
    private String socityId;
    @SerializedName("house_id")
    @Expose
    private String houseId;
    @SerializedName("house_name")
    @Expose
    private String houseName;
    @SerializedName("gate_id")
    @Expose
    private String gateId;
    @SerializedName("gatekeeper_name")
    @Expose
    private Object gatekeeperName;
    @SerializedName("member_name")
    @Expose
    private String memberName;
    @SerializedName("meeting_purpose")
    @Expose
    private String meetingPurpose;
    @SerializedName("userpic")
    @Expose
    private String userpic;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("status")
    @Expose
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getGateId() {
        return gateId;
    }

    public void setGateId(String gateId) {
        this.gateId = gateId;
    }

    public Object getGatekeeperName() {
        return gatekeeperName;
    }

    public void setGatekeeperName(Object gatekeeperName) {
        this.gatekeeperName = gatekeeperName;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMeetingPurpose() {
        return meetingPurpose;
    }

    public void setMeetingPurpose(String meetingPurpose) {
        this.meetingPurpose = meetingPurpose;
    }

    public String getUserpic() {
        return userpic;
    }

    public void setUserpic(String userpic) {
        this.userpic = userpic;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
