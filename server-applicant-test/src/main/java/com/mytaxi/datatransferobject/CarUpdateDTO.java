package com.mytaxi.datatransferobject;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mytaxi.domainobject.EngineTypeEnum;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarUpdateDTO {

    @JsonIgnore
    private Long id;

    @NotNull(message = "Color can not be null!")
    private String color;

    @NotNull(message = "License Plate can not be null!")
    private String licensePlate;

    @NotNull(message = "Rting can not be null!")
    private Double rating;

    @NotNull(message = "Engine Type can not be null!")
    private EngineTypeEnum engineType;

    public CarUpdateDTO(String color, String licensePlate, Double rating,
	    EngineTypeEnum engineType) {
	super();
	this.color = color;
	this.licensePlate = licensePlate;
	this.rating = rating;
	this.engineType = engineType;
    }

    private CarUpdateDTO() {
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

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }
}
