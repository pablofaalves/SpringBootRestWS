package com.mytaxi.domainobject;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.stream.Stream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "car", uniqueConstraints = @UniqueConstraint(name = "uc_licensePlate", columnNames = { "licensePlate" }))
public class CarDO {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "Manufacturer can not be null!")
    private String manufacturer;

    @Column(nullable = false)
    @NotNull(message = "Model can not be null!")
    private String model;

    @Column(nullable = false)
    @NotNull(message = "Color can not be null!")
    private String color;

    @Column(nullable = false)
    @NotNull(message = "License Plate can not be null!")
    private String licensePlate;

    @Column(nullable = false)
    @NotNull(message = "Seat Count can not be null!")
    private Short seatCount;

    @Column(nullable = false)
    private Boolean convertible;

    @Column(nullable = false)
    @NotNull(message = "Rating can not be null!")
    private Double rating;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Engine Type can not be null!")
    private EngineTypeEnum engineType;

    @OneToOne(mappedBy = "car")
    private DriverDO driver;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime lastModified;

    private CarDO() {
    }

    public CarDO(String manufacturer, String model, String color,
	    String licensePlate, Short seatCount, Boolean convertible,
	    Double rating, EngineTypeEnum engineType) {
	super();
	this.manufacturer = manufacturer;
	this.model = model;
	this.color = color;
	this.licensePlate = licensePlate;
	this.seatCount = seatCount;
	this.convertible = convertible;
	this.rating = rating;
	this.engineType = engineType;
    }

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

    public ZonedDateTime getDateCreated() {
	return dateCreated;
    }

    public void setDateCreated(ZonedDateTime dateCreated) {
	this.dateCreated = dateCreated;
    }

    public ZonedDateTime getLastModified() {
	return lastModified;
    }

    public void setLastModified(ZonedDateTime lastModified) {
	this.lastModified = lastModified;
    }

    public DriverDO getDriver() {
	return driver;
    }

    @Override
    public String toString() {
	return "CarDO [id=" + id + ", manufacturer=" + manufacturer
		+ ", model=" + model + ", color=" + color + ", licensePlate="
		+ licensePlate + ", seatCount=" + seatCount + ", convertible="
		+ convertible + ", rating=" + rating + ", engineType="
		+ engineType + ", driver=" + driver + ", dateCreated="
		+ dateCreated + ", lastModified=" + lastModified + "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	CarDO other = (CarDO) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	return true;
    }

    public boolean isNull() {
	return Stream.of(id, manufacturer, model, color, licensePlate,
		seatCount, convertible, rating, engineType, dateCreated,
		lastModified).allMatch(Objects::isNull);
    }
}
