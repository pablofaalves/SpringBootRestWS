package com.mytaxi.dataaccessobject;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.mytaxi.domainobject.CarDO;

/**
 * Database Access Object for car table.
 * <p/>
 */
public interface CarRepository extends PagingAndSortingRepository<CarDO, Long> {
}
