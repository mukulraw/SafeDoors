package com.technobrix.tbx.safedoors.BookFacilityPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tvs on 11/13/2017.
 */

public class Datum {


    @SerializedName("book_id")
    @Expose
    private String bookId;
    @SerializedName("book_by")
    @Expose
    private String bookBy;
    @SerializedName("house_no")
    @Expose
    private String houseNo;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("from_time")
    @Expose
    private String fromTime;
    @SerializedName("to_time")
    @Expose
    private String toTime;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("booking_request_date")
    @Expose
    private String bookingRequestDate;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookBy() {
        return bookBy;
    }

    public void setBookBy(String bookBy) {
        this.bookBy = bookBy;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBookingRequestDate() {
        return bookingRequestDate;
    }

    public void setBookingRequestDate(String bookingRequestDate) {
        this.bookingRequestDate = bookingRequestDate;
    }


}
