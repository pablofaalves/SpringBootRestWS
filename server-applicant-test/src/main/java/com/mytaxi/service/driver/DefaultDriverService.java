package com.mytaxi.service.driver;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.mytaxi.dataaccessobject.DriverRepository;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.GeoCoordinate;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EmptySearchResultException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.exception.OperationNotAllowedException;
import com.mytaxi.service.car.CarService;

/**
 * Service to encapsulate the link between DAO and controller and to have
 * business logic for some driver specific things.
 * <p/>
 */
@Service
public class DefaultDriverService implements DriverService {

    private static final Logger LOG = LoggerFactory
	    .getLogger(DefaultDriverService.class);

    private final DriverRepository driverRepository;
    private final CarService carService;

    public DefaultDriverService(final DriverRepository driverRepository,
	    final CarService carService) {
	this.driverRepository = driverRepository;
	this.carService = carService;
    }

    /**
     * Selects a driver by id.
     *
     * @param driverId
     * @return found driver
     * @throws EntityNotFoundException
     *             if no driver with the given id was found.
     */
    @Override
    public DriverDO find(Long driverId) throws EntityNotFoundException {
	return findDriverChecked(driverId);
    }

    /**
     * Creates a new driver.
     *
     * @param driverDO
     * @return
     * @throws ConstraintsViolationException
     *             if a driver already exists with the given username, ... .
     */
    @Override
    public DriverDO create(DriverDO driverDO)
	    throws ConstraintsViolationException {
	DriverDO driver;
	try {
	    driverDO.setDateCreated(ZonedDateTime.now());
	    driverDO.setDeleted(false);
	    driverDO.setOnlineStatus(OnlineStatus.OFFLINE);
	    driver = driverRepository.save(driverDO);
	} catch (DataIntegrityViolationException e) {
	    LOG.warn(
		    "ConstraintsViolationException while creating a driver: {}",
		    driverDO, e);
	    throw new ConstraintsViolationException(e.getMessage());
	}
	return driver;
    }

    /**
     * Deletes an existing driver by id.
     *
     * @param driverId
     * @throws EntityNotFoundException
     *             if no driver with the given id was found.
     */
    @Override
    @Transactional
    public void delete(Long driverId) throws EntityNotFoundException {
	DriverDO driverDO = findDriverChecked(driverId);
	driverDO.setDeleted(true);
    }

    /**
     * Update the location for a driver.
     *
     * @param driverId
     * @param longitude
     * @param latitude
     * @throws EntityNotFoundException
     */
    @Override
    @Transactional
    public void updateLocation(long driverId, double longitude, double latitude)
	    throws EntityNotFoundException {
	DriverDO driverDO = findDriverChecked(driverId);
	driverDO.setCoordinate(new GeoCoordinate(latitude, longitude));
	driverDO.setDateCoordinateUpdated(ZonedDateTime.now());
    }

    /**
     * Find all drivers by online state.
     *
     * @param onlineStatus
     */
    @Override
    public List<DriverDO> find(OnlineStatus onlineStatus) {
	return driverRepository.findByOnlineStatus(onlineStatus);
    }

    private DriverDO findDriverChecked(Long driverId)
	    throws EntityNotFoundException {
	return driverRepository.findById(driverId).orElseThrow(
		() -> new EntityNotFoundException(
			"Could not find entity with id: " + driverId));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void selectCar(Long driverId, Long carId)
	    throws EntityNotFoundException, OperationNotAllowedException,
	    CarAlreadyInUseException {

	DriverDO driver = find(driverId);

	if (OnlineStatus.OFFLINE.equals(driver.getOnlineStatus())) {
	    LOG.warn(
		    "OperationNotAllowedException while selection a car for the OFFLINE driver: {}",
		    driver);
	    throw new OperationNotAllowedException();
	}

	// Find the car for selection
	CarDO selectedCar = carService.find(carId);

	// Checks whether the car is in use by another ONLINE driver
	DriverDO existingDriver = driverRepository.findDriverByCar(selectedCar);
	if (existingDriver != null && !existingDriver.equals(driver)
		&& OnlineStatus.ONLINE.equals(existingDriver.getOnlineStatus())) {
	    throw new CarAlreadyInUseException(selectedCar.getManufacturer(),
		    selectedCar.getModel());
	}

	driver.setCar(selectedCar);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deselectCar(Long driverId) throws EntityNotFoundException {
	DriverDO driver = find(driverId);
	driver.setCar(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DriverDO> searchDriversByFilter(DriverDO driverFilter)
	    throws EmptySearchResultException {
	List<DriverDO> list = new ArrayList<DriverDO>();
	driverRepository.findAll(Example.of(driverFilter),
		Sort.by("username").descending()).forEach(list::add);
	if (CollectionUtils.isEmpty(list)) {
	    throw new EmptySearchResultException();
	}
	
	return list;
    }
}
