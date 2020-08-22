package com.technobrix.tbx.safedoors.BookFacilityPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.technobrix.tbx.safedoors.FacilityPOJO.FacilityList;

import java.util.List;

/**
 * Created by tvs on 11/13/2017.
 */

public class BookBean {


    @SerializedName("facility_list")
    @Expose
    private List<FacilityList> facilityList = null;

    public List<FacilityList> getFacilityList() {
        return facilityList;
    }

    public void setFacilityList(List<FacilityList> facilityList) {
        this.facilityList = facilityList;
    }


}
