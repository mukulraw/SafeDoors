package com.technobrix.tbx.safedoors.FamilyPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class FamiltList {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("relation")
    @Expose
    private String relation;
    @SerializedName("add_by_id")
    @Expose
    private String addById;
    @SerializedName("add_by_name")
    @Expose
    private String addByName;
    @SerializedName("age")
    @Expose
    private String age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getAddById() {
        return addById;
    }

    public void setAddById(String addById) {
        this.addById = addById;
    }

    public String getAddByName() {
        return addByName;
    }

    public void setAddByName(String addByName) {
        this.addByName = addByName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

}
