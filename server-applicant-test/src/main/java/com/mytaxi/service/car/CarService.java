package com.mytaxi.service.car;

import java.util.List;

import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;

public interface CarService {

    /**
     * Find a car by its ID.
     * 
     * @param carId
     * @return
     * @throws EntityNotFoundException
     *             If no car with the given id was found
     */
    CarDO find(Long carId) throws EntityNotFoundException;

    /**
     * Find All cars
     * 
     * @return
     */
    List<CarDO> findAll();

    /**
     * Create a new car
     * 
     * @param CarDO
     * @return
     * @throws ConstraintsViolationException
     */
    CarDO create(CarDO carDO) throws ConstraintsViolationException;

    /**
     * Update a car information.
     * 
     * @param CarDO
     * @return
     * @throws EntityNotFoundException
     */
    void update(CarDO carDO) throws EntityNotFoundException;

    /**
     * Deletion of a car
     * 
     * @param carId
     * @throws EntityNotFoundException
     */
    void delete(Long carId) throws EntityNotFoundException,
	    CarAlreadyInUseException;
}
