package com.technobrix.tbx.safedoors.TopicPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tvs on 10/24/2017.
 */

public class TopiBean {

    @SerializedName("topic_list")
    @Expose
    private List<TopicList> topicList = null;

    public List<TopicList> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<TopicList> topicList) {
        this.topicList = topicList;
    }


}
