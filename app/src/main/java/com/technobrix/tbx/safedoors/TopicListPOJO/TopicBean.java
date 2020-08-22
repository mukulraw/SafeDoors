package com.technobrix.tbx.safedoors.TopicListPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tvs on 10/23/2017.
 */

public class TopicBean {

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
