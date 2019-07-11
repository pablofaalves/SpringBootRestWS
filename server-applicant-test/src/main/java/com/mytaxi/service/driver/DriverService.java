package com.mytaxi.service.driver;

import java.util.List;

import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EmptySearchResultException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.exception.OperationNotAllowedException;

public interface DriverService {

    DriverDO find(Long driverId) throws EntityNotFoundException;

    DriverDO create(DriverDO driverDO) throws ConstraintsViolationException;

    void delete(Long driverId) throws EntityNotFoundException;

    void updateLocation(long driverId, double longitude, double latitude)
	    throws EntityNotFoundException;

    List<DriverDO> find(OnlineStatus onlineStatus);

    /**
     * Select a car for a driver.
     * 
     * @param driverId
     * @param carId
     * @throws EntityNotFoundException
     * @throws OperationNotAllowedException
     *             if driver is offline
     * @throws CarAlreadyInUseException
     *             If a car is being used by another driver whose OnlineStatus
     *             is ONLINE
     */
    void selectCar(Long driverId, Long carId) throws EntityNotFoundException,
	    OperationNotAllowedException, CarAlreadyInUseException;

    /**
     * Sets null to the car associated to a driver.
     * 
     * @param driverId
     * @throws EntityNotFoundException
     */
    void deselectCar(Long driverId) throws EntityNotFoundException;

    /**
     * Search for drivers with filters.
     * 
     * @param driverFilter
     * @return
     * @throws EmptySearchResultException
     */
    List<DriverDO> searchDriversByFilter(DriverDO driverFilter)
	    throws EmptySearchResultException;
}
