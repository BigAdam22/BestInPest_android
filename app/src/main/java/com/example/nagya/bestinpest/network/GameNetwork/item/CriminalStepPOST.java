package com.example.nagya.bestinpest.network.GameNetwork.item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CriminalStepPOST {

@SerializedName("arrivalJunctionId")
@Expose
private String arrivalJunctionId;
@SerializedName("departureJunctionId")
@Expose
private String departureJunctionId;
@SerializedName("routeId")
@Expose
private Integer routeId;


public CriminalStepPOST(String arrivalJunctionId, String departureJunctionId,Integer routeId){
    this.arrivalJunctionId= arrivalJunctionId;
    this.departureJunctionId= departureJunctionId;
    this.routeId= routeId;

}

public String getArrivalJunctionId() {
return arrivalJunctionId;
}

public void setArrivalJunctionId(String arrivalJunctionId) {
this.arrivalJunctionId = arrivalJunctionId;
}

public String getDepartureJunctionId() {
return departureJunctionId;
}

public void setDepartureJunctionId(String departureJunctionId) {
this.departureJunctionId = departureJunctionId;
}

public Integer getRouteId() {
return routeId;
}

public void setRouteId(Integer routeId) {
this.routeId = routeId;
}

}