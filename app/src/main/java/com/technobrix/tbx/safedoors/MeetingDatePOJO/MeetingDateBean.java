package com.technobrix.tbx.safedoors.MeetingDatePOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tvs on 11/10/2017.
 */

public class MeetingDateBean {

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
