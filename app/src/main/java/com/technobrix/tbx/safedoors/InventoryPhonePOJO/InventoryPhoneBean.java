package com.technobrix.tbx.safedoors.InventoryPhonePOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tvs on 11/15/2017.
 */

public class InventoryPhoneBean {

    @SerializedName("phone")
    @Expose
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
