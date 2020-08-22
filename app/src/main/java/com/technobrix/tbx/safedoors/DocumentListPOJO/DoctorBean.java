package com.technobrix.tbx.safedoors.DocumentListPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tvs on 10/24/2017.
 */

public class DoctorBean {

    @SerializedName("document_list")
    @Expose
    private List<DocumentList> documentList = null;

    public List<DocumentList> getDocumentList() {
        return documentList;
    }

    public void setDocumentList(List<DocumentList> documentList) {
        this.documentList = documentList;
    }


}
