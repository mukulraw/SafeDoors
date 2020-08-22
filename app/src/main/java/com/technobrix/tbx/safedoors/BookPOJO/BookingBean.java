package com.technobrix.tbx.safedoors.BookPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tvs on 11/17/2017.
 */

public class BookingBean {

    @SerializedName("facility_book_list")
    @Expose
    private List<FacilityBookList> facilityBookList = null;

    public List<FacilityBookList> getFacilityBookList() {
        return facilityBookList;
    }

    public void setFacilityBookList(List<FacilityBookList> facilityBookList) {
        this.facilityBookList = facilityBookList;
    }

}
