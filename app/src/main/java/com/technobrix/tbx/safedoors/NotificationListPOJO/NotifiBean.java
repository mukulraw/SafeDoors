package com.technobrix.tbx.safedoors.NotificationListPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tvs on 11/17/2017.
 */

public class NotifiBean {

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
