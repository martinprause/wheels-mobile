package com.doit.wheels.services.impl;

import com.doit.wheels.dao.entities.Order;
import com.doit.wheels.dao.repositories.GenericRepository;
import com.doit.wheels.dao.repositories.OrderRepository;
import com.doit.wheels.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends GenericServiceImpl<Order> implements OrderService{

    @Autowired
    OrderRepository orderRepository;

    public OrderServiceImpl(GenericRepository<Order> genericRepository) {
        super(genericRepository);
    }

    @Override
    public Order findOrderByOrderNo(String orderNo) {
        return orderRepository.findOrderByOrderNo(orderNo);
    }
}
