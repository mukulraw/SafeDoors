package com.technobrix.tbx.safedoors.GetNotificationPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;



public class NotificationBean {

    @SerializedName("notification_list")
    @Expose
    private List<NotificationList> notificationList = null;

    public List<NotificationList> getNotificationList() {
        return notificationList;
    }

    public void setNotificationList(List<NotificationList> notificationList) {
        this.notificationList = notificationList;
    }
}
