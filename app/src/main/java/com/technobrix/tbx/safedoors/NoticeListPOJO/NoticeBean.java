package com.technobrix.tbx.safedoors.NoticeListPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.technobrix.tbx.safedoors.NotificationListPOJO.NotificationList;


import java.util.List;



public class NoticeBean {


    @SerializedName("notice_list")
    @Expose
    private List<NoticeList> noticeList = null;

    public List<NoticeList> getNoticeList() {
        return noticeList;
    }

    public void setNoticeList(List<NoticeList> noticeList) {
        this.noticeList = noticeList;
    }

}
