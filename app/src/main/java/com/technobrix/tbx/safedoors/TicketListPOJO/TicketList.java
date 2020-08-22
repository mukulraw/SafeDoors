package com.technobrix.tbx.safedoors.TicketListPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tvs on 11/15/2017.
 */

public class TicketList {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("ticket_no")
    @Expose
    private String ticketNo;
    @SerializedName("house_no")
    @Expose
    private Object houseNo;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("meeting_date")
    @Expose
    private String meetingDate;
    @SerializedName("meeting_time")
    @Expose
    private String meetingTime;
    @SerializedName("createdate")
    @Expose
    private String createdate;
    @SerializedName("meeting_schedulr_type")
    @Expose
    private String meetingSchedulrType;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("event_id")
    @Expose
    private String eventId;
    @SerializedName("event_title")
    @Expose
    private String eventTitle;
    @SerializedName("event_price")
    @Expose
    private String eventPrice;
    @SerializedName("buy_date")
    @Expose
    private String buyDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public Object getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(Object houseNo) {
        this.houseNo = houseNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate;
    }

    public String getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTime(String meetingTime) {
        this.meetingTime = meetingTime;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getMeetingSchedulrType() {
        return meetingSchedulrType;
    }

    public void setMeetingSchedulrType(String meetingSchedulrType) {
        this.meetingSchedulrType = meetingSchedulrType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventPrice() {
        return eventPrice;
    }

    public void setEventPrice(String eventPrice) {
        this.eventPrice = eventPrice;
    }

    public String getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }

}
