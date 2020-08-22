package com.technobrix.tbx.safedoors.StaffListPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TBX on 10/26/2017.
 */

public class StaffList {

    @SerializedName("staff_id")
    @Expose
    private String staffId;
    @SerializedName("staff_staus")
    @Expose
    private String staffStaus;
    @SerializedName("staff_name")
    @Expose
    private String staffName;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("professtion")
    @Expose
    private String professtion;

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffStaus() {
        return staffStaus;
    }

    public void setStaffStaus(String staffStaus) {
        this.staffStaus = staffStaus;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfesstion() {
        return professtion;
    }

    public void setProfesstion(String professtion) {
        this.professtion = professtion;
    }

}
