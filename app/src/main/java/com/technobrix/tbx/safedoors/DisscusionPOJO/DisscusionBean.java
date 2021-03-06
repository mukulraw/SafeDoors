package com.technobrix.tbx.safedoors.DisscusionPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tvs on 10/23/2017.
 */

public class DisscusionBean {

    @SerializedName("member_list")
    @Expose
    private List<MemberList> memberList = null;

    public List<MemberList> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<MemberList> memberList) {
        this.memberList = memberList;
    }

}
