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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.CarUpdateDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.car.CarService;
import com.mytaxi.util.ModelMapperHelper;

@Api(value = "CarController")
@RestController
@RequestMapping("v1/cars")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(final CarService carService) {
	this.carService = carService;
    }

    @ApiOperation(value = "Get a Car by ID", response = CarDTO.class)
    @ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Success|OK"),
	    @ApiResponse(code = 400, message = "Car ID doesn't exist|Bad Request!") })
    @GetMapping("/{carId}")
    public CarDTO getCar(@PathVariable Long carId)
	    throws EntityNotFoundException {
	return ModelMapperHelper.map(carService.find(carId), CarDTO.class);
    }

    @ApiOperation(value = "Create a new Car", response = CarDTO.class)
    @ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Success|OK"),
	    @ApiResponse(code = 400, message = "License Plate already exists|Bad Request!") })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarDTO saveCar(@Valid @RequestBody CarDTO driverDTO)
	    throws ConstraintsViolationException {
	CarDO driverDO = ModelMapperHelper.map(driverDTO, CarDO.class);
	return ModelMapperHelper.map(carService.create(driverDO), CarDTO.class);
    }

    @ApiOperation(value = "Delete an existing Car by Id")
    @ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Success|OK"),
	    @ApiResponse(code = 400, message = "Car ID doesn't exist|Bad Request!"),
	    @ApiResponse(code = 409, message = "Car in use by a Driver|Conflict!") })
    @DeleteMapping("/{carId}")
    public void deleteCar(@PathVariable Long carId)
	    throws EntityNotFoundException, CarAlreadyInUseException {
	carService.delete(carId);
    }

    @ApiOperation(value = "Update a Car informations")
    @ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Success|OK"),
	    @ApiResponse(code = 400, message = "Car ID doesn't exist|Bad Request!") })
    @PutMapping("/{carId}")
    public void updateCar(@Valid @RequestBody CarUpdateDTO carDTO,
	    @PathVariable Long id) throws EntityNotFoundException {
	carDTO.setId(id);
	carService.update(ModelMapperHelper.map(carDTO, CarDO.class));
    }

    @ApiOperation(value = "Get all available Cars")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success|OK") })
    @GetMapping
    public List<CarDTO> findAllCars() {
	return ModelMapperHelper.mapList(carService.findAll(), CarDTO.class);
    }
}
