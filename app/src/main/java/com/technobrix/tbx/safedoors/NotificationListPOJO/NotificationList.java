package com.technobrix.tbx.safedoors.NotificationListPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tvs on 11/17/2017.
 */

public class NotificationList {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("notify_data")
    @Expose
    private NotifyData notifyData;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public NotifyData getNotifyData() {
        return notifyData;
    }

    public void setNotifyData(NotifyData notifyData) {
        this.notifyData = notifyData;
    }


}
