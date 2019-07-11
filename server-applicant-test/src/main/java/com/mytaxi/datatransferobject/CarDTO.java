package com.mytaxi.datatransferobject;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mytaxi.domainobject.EngineTypeEnum;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO {

    @JsonIgnore
    private Long id;

    @NotNull(message = "Manufacturer can not be null!")
    private String manufacturer;

    @NotNull(message = "Model can not be null!")
    private String model;

    @NotNull(message = "Color can not be null!")
    private String color;

    @NotNull(message = "License Plate can not be null!")
    private String licensePlate;

    @NotNull(message = "Seat Count can not be null!")
    private Short seatCount;

    @NotNull(message = "Convertible can not be null!")
    private Boolean convertible;

    @NotNull(message = "Rating can not be null!")
    private Double rating;

    @NotNull(message = "Engine Type can not be null!")
    private EngineTypeEnum engineType;

    private CarDTO() {
    }

    public CarDTO(Long id, String manufacturer, String model, String color,
	    String licensePlate, Short seatCount, Boolean convertible,
	    Double rating, EngineTypeEnum engineType) {
	super();
	this.id = id;
	this.manufacturer = manufacturer;
	this.licensePlate = licensePlate;
	this.model = model;
	this.color = color;
	this.seatCount = seatCount;
	this.convertible = convertible;
	this.rating = rating;
	this.engineType = engineType;
    }

    public static CarDTOBuilder newBuilder() {
	return new CarDTOBuilder();
    }

    @JsonProperty
    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
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

    public static class CarDTOBuilder {
	private Long id;
	private String manufacturer;
	private String model;
	private String color;
	private String licensePlate;
	private Short seatCount;
	private Boolean convertible;
	private Double rating;
	private EngineTypeEnum engineType;

	public CarDTOBuilder setId(Long id) {
	    this.id = id;
	    return this;
	}

	public CarDTOBuilder setManufacturer(String manufacturer) {
	    this.manufacturer = manufacturer;
	    return this;
	}

	public CarDTOBuilder setModel(String model) {
	    this.model = model;
	    return this;
	}

	public CarDTOBuilder setColor(String color) {
	    this.color = color;
	    return this;
	}

	public CarDTOBuilder setLicensePlate(String licensePlate) {
	    this.licensePlate = licensePlate;
	    return this;
	}

	public CarDTOBuilder setSeatCount(Short seatCount) {
	    this.seatCount = seatCount;
	    return this;
	}

	public CarDTOBuilder setConvertible(Boolean convertible) {
	    this.convertible = convertible;
	    return this;
	}

	public CarDTOBuilder setRating(Double rating) {
	    this.rating = rating;
	    return this;
	}

	public CarDTOBuilder setEngineType(EngineTypeEnum engineType) {
	    this.engineType = engineType;
	    return this;
	}

	public CarDTO createCarDTO() {
	    return new CarDTO(id, manufacturer, model, color, licensePlate,
		    seatCount, convertible, rating, engineType);
	}

    }
}
