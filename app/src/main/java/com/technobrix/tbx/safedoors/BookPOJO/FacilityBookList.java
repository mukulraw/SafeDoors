package com.technobrix.tbx.safedoors.BookPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tvs on 11/17/2017.
 */

public class FacilityBookList {


    @SerializedName("booking_id")
    @Expose
    private String bookingId;
    @SerializedName("booking_date")
    @Expose
    private String bookingDate;
    @SerializedName("booking_request_date")
    @Expose
    private String bookingRequestDate;
    @SerializedName("book_by")
    @Expose
    private String bookBy;
    @SerializedName("facility_name")
    @Expose
    private String facilityName;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("from_time")
    @Expose
    private String fromTime;
    @SerializedName("to_time")
    @Expose
    private String toTime;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("status")
    @Expose
    private String status;

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBookingRequestDate() {
        return bookingRequestDate;
    }

    public void setBookingRequestDate(String bookingRequestDate) {
        this.bookingRequestDate = bookingRequestDate;
    }

    public String getBookBy() {
        return bookBy;
    }

    public void setBookBy(String bookBy) {
        this.bookBy = bookBy;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
