package com.technobrix.tbx.safedoors.CommentListPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tvs on 10/24/2017.
 */

public class CommentsBean {

    @SerializedName("comment_list")
    @Expose
    private List<CommentList> commentList = null;

    public List<CommentList> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentList> commentList) {
        this.commentList = commentList;
    }

}
