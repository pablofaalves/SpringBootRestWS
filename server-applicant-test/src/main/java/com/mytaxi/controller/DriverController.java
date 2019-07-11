package com.mytaxi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.datatransferobject.DriverFilterDTO;
import com.mytaxi.datatransferobject.DriverSearchResultDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EmptySearchResultException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.exception.OperationNotAllowedException;
import com.mytaxi.service.driver.DriverService;
import com.mytaxi.util.ModelMapperHelper;

/**
 * All operations with a driver will be routed by this controller.
 * <p/>
 */
@Api(value = "DriverController")
@RestController
@RequestMapping("v1/drivers")
public class DriverController {

    private final DriverService driverService;

    @Autowired
    public DriverController(final DriverService driverService) {
	this.driverService = driverService;
    }

    @ApiOperation(value = "Get a Driver by ID", httpMethod = "GET", response = DriverDTO.class)
    @ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Success|OK"),
	    @ApiResponse(code = 400, message = "Driver ID doesn't exist|Bad Request!") })
    @GetMapping("/{driverId}")
    public DriverDTO getDriver(@PathVariable long driverId)
	    throws EntityNotFoundException {
	return ModelMapperHelper.map(driverService.find(driverId),
		DriverDTO.class);
    }

    @ApiOperation(value = "Create a new Driver", httpMethod = "POST", response = DriverDTO.class)
    @ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Success|OK"),
	    @ApiResponse(code = 400, message = "Driver user name alread exist|Bad Request!") })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DriverDTO createDriver(@Valid @RequestBody DriverDTO driverDTO)
	    throws ConstraintsViolationException {
	DriverDO driverDO = ModelMapperHelper.map(driverDTO, DriverDO.class);
	return ModelMapperHelper.map(driverService.create(driverDO),
		DriverDTO.class);
    }

    @ApiOperation(value = "Logical deletion of a Driver", httpMethod = "DELETE")
    @ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Success|OK"),
	    @ApiResponse(code = 400, message = "Driver ID doesn't exist|Bad Request!") })
    @DeleteMapping("/{driverId}")
    public void deleteDriver(@PathVariable long driverId)
	    throws EntityNotFoundException {
	driverService.delete(driverId);
    }

    @ApiOperation(value = "Update a Driver's geolocation", httpMethod = "PUT")
    @ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Success|OK"),
	    @ApiResponse(code = 400, message = "Driver ID doesn't exist|Bad Request!") })
    @PutMapping("/{driverId}")
    public void updateLocation(@PathVariable long driverId,
	    @RequestParam double longitude, @RequestParam double latitude)
	    throws EntityNotFoundException {
	driverService.updateLocation(driverId, longitude, latitude);
    }

    @ApiOperation(value = "Get all drivers by their online status", httpMethod = "GET")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK") })
    @GetMapping
    public List<DriverDTO> findDrivers(@RequestParam OnlineStatus onlineStatus) {
	return ModelMapperHelper.mapList(driverService.find(onlineStatus),
		DriverDTO.class);
    }

    @ApiOperation(value = "Select a Car for a Driver", httpMethod = "PUT")
    @ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Success|OK"),
	    @ApiResponse(code = 400, message = "Driver ID or Car ID doesn't exist|Bad Request!"),
	    @ApiResponse(code = 403, message = "A offline Driver cannot select a Car|Forbidden!"),
	    @ApiResponse(code = 409, message = "Car already selected by another online Driver|Conflict!") })
    @PutMapping("/selectcar/{driverId}/{carId}")
    public void selectCar(@PathVariable long driverId, @PathVariable long carId)
	    throws EntityNotFoundException, OperationNotAllowedException,
	    CarAlreadyInUseException {
	driverService.selectCar(driverId, carId);
    }

    @ApiOperation(value = "Removes Car selection for a Driver", httpMethod = "PUT")
    @ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Success|OK"),
	    @ApiResponse(code = 400, message = "Driver ID doesn't exist|Bad Request!") })
    @PutMapping("/deselectcar/{driverId}")
    public void deselectCar(@PathVariable long driverId)
	    throws EntityNotFoundException {
	driverService.deselectCar(driverId);
    }

    @ApiOperation(value = "Searches for drivers according to driver and car characteristics", response = DriverSearchResultDTO.class, httpMethod = "GET")
    @ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Success|OK"),
	    @ApiResponse(code = 400, message = "Driver ID doesn't exist|Bad Request!") })
    @GetMapping("/search")
    public List<DriverSearchResultDTO> searchByFilter(DriverFilterDTO filter)
	    throws EmptySearchResultException {
	DriverDO driverFilter = ModelMapperHelper.map(filter, DriverDO.class);
	CarDO car = ModelMapperHelper.map(filter, CarDO.class);

	// Checks whether car filters have been filled, otherwise, sets car
	// attribute on driverDO to null
	driverFilter.setCar(!car.isNull() ? car : null);

	return ModelMapperHelper.mapList(
		driverService.searchDriversByFilter(driverFilter),
		DriverSearchResultDTO.class);
    }
}
