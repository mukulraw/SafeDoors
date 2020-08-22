package com.technobrix.tbx.safedoors.NotificationListPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tvs on 11/17/2017.
 */

public class NotifyData {


    @SerializedName("visitor_id")
    @Expose
    private String visitorId;
    @SerializedName("socity_id")
    @Expose
    private String socityId;
    @SerializedName("gate_id")
    @Expose
    private String gateId;
    @SerializedName("visitor_type")
    @Expose
    private String visitorType;
    @SerializedName("gate_keeper_name")
    @Expose
    private String gateKeeperName;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("intime")
    @Expose
    private String intime;
    @SerializedName("outtime")
    @Expose
    private String outtime;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("visitor_name")
    @Expose
    private String visitorName;
    @SerializedName("pic")
    @Expose
    private String pic;
    @SerializedName("house_id")
    @Expose
    private String houseId;
    @SerializedName("house_no")
    @Expose
    private String houseNo;
    @SerializedName("staff_id")
    @Expose
    private String staffId;
    @SerializedName("staff_name")
    @Expose
    private String staffName;

    public String getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
    }

    public String getSocityId() {
        return socityId;
    }

    public void setSocityId(String socityId) {
        this.socityId = socityId;
    }

    public String getGateId() {
        return gateId;
    }

    public void setGateId(String gateId) {
        this.gateId = gateId;
    }

    public String getVisitorType() {
        return visitorType;
    }

    public void setVisitorType(String visitorType) {
        this.visitorType = visitorType;
    }

    public String getGateKeeperName() {
        return gateKeeperName;
    }

    public void setGateKeeperName(String gateKeeperName) {
        this.gateKeeperName = gateKeeperName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIntime() {
        return intime;
    }

    public void setIntime(String intime) {
        this.intime = intime;
    }

    public String getOuttime() {
        return outtime;
    }

    public void setOuttime(String outtime) {
        this.outtime = outtime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
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

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }


}
