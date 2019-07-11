package com.mytaxi.datatransferobject;

import com.mytaxi.domainobject.EngineTypeEnum;
import com.mytaxi.domainvalue.OnlineStatus;

public class DriverFilterDTO {

    private String username;
    private OnlineStatus onlineStatus;
    private String manufacturer;
    private String model;
    private String color;
    private String licensePlate;
    private Short seatCount;
    private Boolean convertible;
    private Double rating;
    private EngineTypeEnum engineType;

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

    public String getManufacturer() {
	return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
	this.manufacturer = manufacturer;
    }

    public String getModel() {
	return model;
    }

    public void setModel(String model) {
	this.model = model;
    }

    public String getColor() {
	return color;
    }

    public void setColor(String color) {
	this.color = color;
    }

    public String getLicensePlate() {
	return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
	this.licensePlate = licensePlate;
    }

    public Short getSeatCount() {
	return seatCount;
    }

    public void setSeatCount(Short seatCount) {
	this.seatCount = seatCount;
    }

    public Boolean getConvertible() {
	return convertible;
    }

    public void setConvertible(Boolean convertible) {
	this.convertible = convertible;
    }

    public Double getRating() {
	return rating;
    }

    public void setRating(Double rating) {
	this.rating = rating;
    }

    public EngineTypeEnum getEngineType() {
	return engineType;
    }

    public void setEngineType(EngineTypeEnum engineType) {
	this.engineType = engineType;
    }

}
