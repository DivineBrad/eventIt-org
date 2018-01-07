package com.example.bradj.eventitorg.Model.Entity;

/**
 * Created by Bradley Blanchard on 2017-12-28.
 */

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Event {
    @SerializedName("eventId")
    @Expose
    private Integer eventId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("reglimit")
    @Expose
    private Integer reglimit;
    @SerializedName("access")
    @Expose
    private String access;

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getReglimit() {
        return reglimit;
    }

    public void setReglimit(Integer reglimit) {
        this.reglimit = reglimit;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

}


