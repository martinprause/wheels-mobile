package com.doit.wheels.services;

import com.doit.wheels.dao.entities.Order;
import com.doit.wheels.dao.entities.WheelRimPosition;

public interface WheelRimPositionService extends GenericService<WheelRimPosition> {

    Order updateStatus(Long id, String status);
}
