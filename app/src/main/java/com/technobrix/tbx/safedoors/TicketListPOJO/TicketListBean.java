package com.technobrix.tbx.safedoors.TicketListPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tvs on 11/15/2017.
 */

public class TicketListBean {

    @SerializedName("ticket_list")
    @Expose
    private List<TicketList> ticketList = null;

    public List<TicketList> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<TicketList> ticketList) {
        this.ticketList = ticketList;
    }
}
