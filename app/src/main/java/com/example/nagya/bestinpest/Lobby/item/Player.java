package com.example.nagya.bestinpest.Lobby.item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.ref.SoftReference;

public class Player {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("junctionId")
    @Expose
    private String junctionId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("ready")
    @Expose
    private Boolean ready;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJunctionId() {
        return junctionId;
    }

    public void setJunctionId(String junctionId) {
        this.junctionId = junctionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getReady() {        return ready;    }

    public void setReady(Boolean ready) {        this.ready = ready;    }

    public Player(String junctionId, String name){
        this.junctionId= junctionId;
        this.name= name;
        ready= false;
    }

}