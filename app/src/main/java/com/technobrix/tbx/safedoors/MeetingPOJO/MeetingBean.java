package com.technobrix.tbx.safedoors.MeetingPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MeetingBean {

    @SerializedName("meeting_list")
    @Expose
    private List<MeetingList> meetingList = null;

    public List<MeetingList> getMeetingList() {
        return meetingList;
    }

    public void setMeetingList(List<MeetingList> meetingList) {
        this.meetingList = meetingList;
    }
}
