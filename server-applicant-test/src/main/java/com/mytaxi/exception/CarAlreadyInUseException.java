package com.mytaxi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Car is already in use by a driver...")
public class CarAlreadyInUseException extends Exception {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    public CarAlreadyInUseException(String carManufacturer, String carModel) {
	super(String.format("The %s %s is already in use by another driver",
		carManufacturer, carModel));
    }

}
