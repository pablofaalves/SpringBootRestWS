package com.mytaxi.service.car;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;

/**
 * Service to encapsulate the link between DAO and controller and to have
 * business logic the Car entity.
 * <p/>
 */
@Service
public class DefaultCarService implements CarService {

    private static final Logger LOG = LoggerFactory
	    .getLogger(DefaultCarService.class);

    private final CarRepository carRepository;

    public DefaultCarService(final CarRepository carRepository) {
	this.carRepository = carRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CarDO find(Long carId) throws EntityNotFoundException {
	return findCarChecked(carId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CarDO create(CarDO carDO) throws ConstraintsViolationException {
	CarDO car;
	try {
	    ZonedDateTime now = ZonedDateTime.now();
	    carDO.setDateCreated(now);
	    carDO.setLastModified(now);
	    car = carRepository.save(carDO);
	} catch (DataIntegrityViolationException e) {
	    LOG.warn("ConstraintsViolationException while creating a car: {}",
		    carDO, e);
	    throw new ConstraintsViolationException(e.getMessage());
	}
	return car;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void delete(Long carId) throws EntityNotFoundException,
	    CarAlreadyInUseException {
	CarDO carDO = findCarChecked(carId);

	if (carDO.getDriver() != null) {
	    throw new CarAlreadyInUseException(carDO.getManufacturer(),
		    carDO.getModel());
	}

	carRepository.delete(carDO);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CarDO> findAll() {
	List<CarDO> carDOList = new ArrayList<CarDO>();
	carRepository.findAll(
		Sort.by(Order.by("manufacturer"), Order.by("model"))).forEach(
		carDOList::add);
	return carDOList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(CarDO carDO) throws EntityNotFoundException {
	CarDO carDODataBase = findCarChecked(carDO.getId());

	carDODataBase.setLicensePlate(carDO.getLicensePlate());
	carDODataBase.setRating(carDO.getRating());
	carDODataBase.setEngineType(carDO.getEngineType());
	carDODataBase.setLastModified(ZonedDateTime.now());
    }

    /**
     * Find a car with the given ID verifying its existence. Throws
     * EntityNotFoundException in case it doesn't.
     * 
     * @param carId
     * @return
     * @throws EntityNotFoundException
     */
    private CarDO findCarChecked(Long carId) throws EntityNotFoundException {
	return carRepository.findById(carId).orElseThrow(
		() -> new EntityNotFoundException(
			"Could not find entity with id: " + carId));
    }
}
