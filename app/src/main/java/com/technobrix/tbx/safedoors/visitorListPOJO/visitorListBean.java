package com.technobrix.tbx.safedoors.visitorListPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TBX on 10/26/2017.
 */

public class visitorListBean {

    @SerializedName("visitor_list")
    @Expose
    private List<VisitorList> visitorList = null;

    public List<VisitorList> getVisitorList() {
        return visitorList;
    }

    public void setVisitorList(List<VisitorList> visitorList) {
        this.visitorList = visitorList;
    }

}
