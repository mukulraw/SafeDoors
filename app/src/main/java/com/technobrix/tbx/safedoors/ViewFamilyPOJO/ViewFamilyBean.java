package com.technobrix.tbx.safedoors.ViewFamilyPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tvs on 11/4/2017.
 */

public class ViewFamilyBean
{

    @SerializedName("familt_list")
    @Expose
    private List<FamiltList> familtList = null;

    public List<FamiltList> getFamiltList() {
        return familtList;
    }

    public void setFamiltList(List<FamiltList> familtList) {
        this.familtList = familtList;
    }
}
