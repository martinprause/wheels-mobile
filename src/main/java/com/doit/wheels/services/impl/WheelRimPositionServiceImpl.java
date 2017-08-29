package com.doit.wheels.services.impl;

import com.doit.wheels.dao.entities.Order;
import com.doit.wheels.dao.entities.WheelRimPosition;
import com.doit.wheels.dao.repositories.GenericRepository;
import com.doit.wheels.services.OrderService;
import com.doit.wheels.services.WheelRimPositionService;
import com.doit.wheels.utils.enums.StatusTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.TreeSet;

@Service
public class WheelRimPositionServiceImpl extends GenericServiceImpl<WheelRimPosition> implements WheelRimPositionService {

    private final OrderService orderService;

    @Autowired
    public WheelRimPositionServiceImpl(GenericRepository<WheelRimPosition> genericRepository, OrderService orderService) {
        super(genericRepository);
        this.orderService = orderService;
    }

    @Override
    public Order updateStatus(Long id, String status) {
        boolean allProcessed = true;
        WheelRimPosition wheelRimPosition = this.findById(id);
        wheelRimPosition.setStatus(StatusTypeEnum.valueOf(status));
        this.update(wheelRimPosition);
        Order order = orderService.findOrderByWheelRimPositions(wheelRimPosition);
        for (WheelRimPosition rimPosition : order.getWheelRimPositions()) {
            if (rimPosition.getStatus().equals(StatusTypeEnum.IN_PROCESS) || rimPosition.getStatus().equals(StatusTypeEnum.PROCESSED)){
                order.setStatus(StatusTypeEnum.IN_PROCESS);
            }

            if (!rimPosition.getStatus().equals(StatusTypeEnum.PROCESSED)){
                allProcessed = false;
            }
        }

        if (allProcessed){
            order.setStatus(StatusTypeEnum.PROCESSED);
        }

        Order updatedOrder = orderService.update(order);
        if (updatedOrder.getWheelRimPositions().size() != 0) {
            Set<WheelRimPosition> sortedWheelRims = new TreeSet<>(updatedOrder.getWheelRimPositions());
            updatedOrder.setWheelRimPositions(sortedWheelRims);
        }
        return updatedOrder;
    }
}
