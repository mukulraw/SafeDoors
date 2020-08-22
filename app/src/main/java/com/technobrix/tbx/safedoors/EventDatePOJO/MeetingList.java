package com.technobrix.tbx.safedoors.EventDatePOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tvs on 9/26/2017.
 */

public class MeetingList {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
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
    @SerializedName("meeting_schedulr_name")
    @Expose
    private String meetingSchedulrName;
    @SerializedName("event_type")
    @Expose
    private String eventType;
    @SerializedName("event_price")
    @Expose
    private String eventPrice;
    @SerializedName("total_ticket")
    @Expose
    private String totalTicket;
    @SerializedName("left_ticket")
    @Expose
    private String leftTicket;
    @SerializedName("book_ticket")
    @Expose
    private String bookTicket;
    @SerializedName("user_ticket_count")
    @Expose
    private String userTicketCount;
    @SerializedName("ticket_list")
    @Expose
    private List<TicketList> ticketList = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getMeetingSchedulrName() {
        return meetingSchedulrName;
    }

    public void setMeetingSchedulrName(String meetingSchedulrName) {
        this.meetingSchedulrName = meetingSchedulrName;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventPrice() {
        return eventPrice;
    }

    public void setEventPrice(String eventPrice) {
        this.eventPrice = eventPrice;
    }

    public String getTotalTicket() {
        return totalTicket;
    }

    public void setTotalTicket(String totalTicket) {
        this.totalTicket = totalTicket;
    }

    public String getLeftTicket() {
        return leftTicket;
    }

    public void setLeftTicket(String leftTicket) {
        this.leftTicket = leftTicket;
    }

    public String getBookTicket() {
        return bookTicket;
    }

    public void setBookTicket(String bookTicket) {
        this.bookTicket = bookTicket;
    }

    public String getUserTicketCount() {
        return userTicketCount;
    }

    public void setUserTicketCount(String userTicketCount) {
        this.userTicketCount = userTicketCount;
    }

    public List<TicketList> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<TicketList> ticketList) {
        this.ticketList = ticketList;
    }
}
