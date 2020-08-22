package com.technobrix.tbx.safedoors.StaffListPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TBX on 10/26/2017.
 */

public class staffListBean {

    @SerializedName("staff_list")
    @Expose
    private List<StaffList> staffList = null;

    public List<StaffList> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<StaffList> staffList) {
        this.staffList = staffList;
    }

}
