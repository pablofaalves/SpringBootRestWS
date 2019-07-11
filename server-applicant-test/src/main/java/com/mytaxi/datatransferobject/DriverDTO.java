package com.mytaxi.datatransferobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mytaxi.domainvalue.GeoCoordinate;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DriverDTO {
    @JsonIgnore
    private Long id;

    @NotNull(message = "Username can not be null!")
    private String username;

    @NotNull(message = "Password can not be null!")
    private String password;

    private GeoCoordinate coordinate;

    @JsonIgnore
    private CarDTO car;

    private DriverDTO() {
    }

    private DriverDTO(Long id, String username, String password,
	    GeoCoordinate coordinate, CarDTO carDTO) {
	this.id = id;
	this.username = username;
	this.password = password;
	this.coordinate = coordinate;
	this.car = carDTO;
    }

    public static DriverDTOBuilder newBuilder() {
	return new DriverDTOBuilder();
    }

    @JsonProperty
    public Long getId() {
	return id;
    }

    public String getUsername() {
	return username;
    }

    public String getPassword() {
	return password;
    }

    public GeoCoordinate getCoordinate() {
	return coordinate;
    }

    @JsonProperty
    public CarDTO getCar() {
	return car;
    }

    public void setCar(CarDTO carDTO) {
	this.car = carDTO;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public void setCoordinate(GeoCoordinate coordinate) {
	this.coordinate = coordinate;
    }

    public static class DriverDTOBuilder {
	private Long id;
	private String username;
	private String password;
	private GeoCoordinate coordinate;
	private CarDTO carDTO;

	public DriverDTOBuilder setId(Long id) {
	    this.id = id;
	    return this;
	}

	public DriverDTOBuilder setUsername(String username) {
	    this.username = username;
	    return this;
	}

	public DriverDTOBuilder setPassword(String password) {
	    this.password = password;
	    return this;
	}

	public DriverDTOBuilder setCoordinate(GeoCoordinate coordinate) {
	    this.coordinate = coordinate;
	    return this;
	}

	public DriverDTOBuilder setCarDTO(CarDTO carDTO) {
	    this.carDTO = carDTO;
	    return this;
	}

	public DriverDTO createDriverDTO() {
	    return new DriverDTO(id, username, password, coordinate, carDTO);
	}

    }

}
