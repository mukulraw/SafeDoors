package com.technobrix.tbx.safedoors.AllMemeberPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tvs on 10/25/2017.
 */

public class MemberList {

    @SerializedName("member_id")
    @Expose
    private String memberId;
    @SerializedName("member_name")
    @Expose
    private String memberName;
    @SerializedName("house_no")
    @Expose
    private String houseNo;
    @SerializedName("house_name")
    @Expose
    private String houseName;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }
}
