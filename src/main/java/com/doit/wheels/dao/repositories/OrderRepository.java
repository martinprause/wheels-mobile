package com.doit.wheels.dao.repositories;

import com.doit.wheels.dao.entities.Order;
import com.doit.wheels.dao.entities.WheelRimPosition;

public interface OrderRepository extends GenericRepository<Order>{

    Order findOrderByOrderNo(String orderNo);

    Order findOrderByWheelRimPositions(WheelRimPosition wheelRimPosition);

}
