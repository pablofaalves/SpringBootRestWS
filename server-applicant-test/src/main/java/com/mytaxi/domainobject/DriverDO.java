package com.mytaxi.domainobject;

import com.mytaxi.domainvalue.GeoCoordinate;
import com.mytaxi.domainvalue.OnlineStatus;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "driver", uniqueConstraints = @UniqueConstraint(name = "uc_username", columnNames = { "username" }))
public class DriverDO {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated;

    @Column(nullable = false)
    @NotNull(message = "Username can not be null!")
    private String username;

    @Column(nullable = false)
    @NotNull(message = "Password can not be null!")
    private String password;

    @Column(nullable = false)
    private Boolean deleted;

    @Embedded
    private GeoCoordinate coordinate;

    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCoordinateUpdated;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OnlineStatus onlineStatus;

    @OneToOne
    @JoinColumn(name = "car")
    private CarDO car;

    private DriverDO() {
    }

    public DriverDO(String username, String password) {
	this.username = username;
	this.password = password;
	this.deleted = false;
	this.coordinate = null;
	this.dateCoordinateUpdated = null;
	this.onlineStatus = OnlineStatus.OFFLINE;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getUsername() {
	return username;
    }

    public String getPassword() {
	return password;
    }

    public Boolean getDeleted() {
	return deleted;
    }

    public void setDeleted(Boolean deleted) {
	this.deleted = deleted;
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
	this.dateCoordinateUpdated = ZonedDateTime.now();
    }

    public CarDO getCar() {
	return car;
    }

    public void setCar(CarDO car) {
	this.car = car;
    }

    public ZonedDateTime getDateCoordinateUpdated() {
	return dateCoordinateUpdated;
    }

    public void setDateCoordinateUpdated(ZonedDateTime dateCoordinateUpdated) {
	this.dateCoordinateUpdated = dateCoordinateUpdated;
    }

    public ZonedDateTime getDateCreated() {
	return dateCreated;
    }

    public void setDateCreated(ZonedDateTime dateCreated) {
	this.dateCreated = dateCreated;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    @Override
    public String toString() {
	return "DriverDO [id=" + id + ", dateCreated=" + dateCreated
		+ ", username=" + username + ", password=" + password
		+ ", deleted=" + deleted + ", coordinate=" + coordinate
		+ ", dateCoordinateUpdated=" + dateCoordinateUpdated
		+ ", onlineStatus=" + onlineStatus + ", car=" + car + "]";
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
	DriverDO other = (DriverDO) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	return true;
    }
}
