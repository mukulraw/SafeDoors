package com.technobrix.tbx.safedoors.GetImagePOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tvs on 10/25/2017.
 */

public class GetBean {


    @SerializedName("profile_image")
    @Expose
    private String profileImage;
    @SerializedName("background_image")
    @Expose
    private String backgroundImage;

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }


}
