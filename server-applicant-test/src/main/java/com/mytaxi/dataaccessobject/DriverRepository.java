package com.mytaxi.dataaccessobject;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;

/**
 * Database Access Object for driver table.
 * <p/>
 */
public interface DriverRepository extends CrudRepository<DriverDO, Long>,
	QueryByExampleExecutor<DriverDO> {

    /**
     * Finds a driver by Online Status.
     * 
     * @param onlineStatus
     * @return List<DriverDO>
     */
    List<DriverDO> findByOnlineStatus(OnlineStatus onlineStatus);

    /**
     * Finds a driver by the car associated
     * 
     * @param car
     * @return DriverDO
     */
    DriverDO findDriverByCar(CarDO car);
}
