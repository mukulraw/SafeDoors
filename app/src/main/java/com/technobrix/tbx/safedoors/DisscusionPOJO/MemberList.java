package com.technobrix.tbx.safedoors.DisscusionPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class MemberList {

    @SerializedName("member_id")
    @Expose
    private String memberId;
    @SerializedName("member_name")
    @Expose
    private String memberName;

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


}
