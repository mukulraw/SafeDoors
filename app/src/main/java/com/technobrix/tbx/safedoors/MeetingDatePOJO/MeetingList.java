package com.technobrix.tbx.safedoors.MeetingDatePOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tvs on 11/10/2017.
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


}
