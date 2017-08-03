package com.doit.wheels.dao.repositories;

import com.doit.wheels.dao.entities.Order;

public interface OrderRepository extends GenericRepository<Order>{

    Order findOrderByOrderNo(String orderNo);
}
