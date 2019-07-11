package com.mytaxi.datatransferobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mytaxi.domainvalue.GeoCoordinate;
import com.mytaxi.domainvalue.OnlineStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DriverSearchResultDTO {

    private Long id;
    private String username;
    private OnlineStatus onlineStatus;
    private GeoCoordinate coordinate;
    private CarDTO car;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public OnlineStatus getOnlineStatus() {
	return onlineStatus;
    }

    public void setOnlineStatus(OnlineStatus onlineStatus) {
	this.onlineStatus = onlineStatus;
    }

    public GeoCoordinate getCoordinate() {
	return coordinate;
    }

    public void setCoordinate(GeoCoordinate coordinate) {
	this.coordinate = coordinate;
    }

    public CarDTO getCar() {
	return car;
    }

    public void setCar(CarDTO car) {
	this.car = car;
    }
}
