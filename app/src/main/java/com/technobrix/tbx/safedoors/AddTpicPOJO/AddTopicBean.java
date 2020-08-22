package com.technobrix.tbx.safedoors.AddTpicPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tvs on 10/24/2017.
 */

public class AddTopicBean {

    @SerializedName("status")
    @Expose
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
