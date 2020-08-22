package com.technobrix.tbx.safedoors;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TBX on 10/26/2017.
 */

public class entryBean {

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
