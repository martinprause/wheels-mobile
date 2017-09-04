package com.doit.wheels.services.impl;

import com.doit.wheels.dao.entities.Order;
import com.doit.wheels.dao.entities.Signature;
import com.doit.wheels.dao.entities.User;
import com.doit.wheels.dao.entities.WheelRimPosition;
import com.doit.wheels.dao.repositories.GenericRepository;
import com.doit.wheels.dao.repositories.OrderRepository;
import com.doit.wheels.services.OrderService;
import com.doit.wheels.services.UserService;
import com.doit.wheels.utils.enums.AccessLevelTypeEnum;
import com.doit.wheels.utils.enums.StatusTypeEnum;
import com.doit.wheels.utils.exceptions.NoPermissionsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl extends GenericServiceImpl<Order> implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;

    @Autowired
    public OrderServiceImpl(GenericRepository<Order> genericRepository, OrderRepository orderRepository, UserService userService) {
        super(genericRepository);
        this.orderRepository = orderRepository;
        this.userService = userService;
    }

    @Override
    public Order save(Order order) {
        User currentUser = userService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if (order.getId() == null) {
            order.setStatus(StatusTypeEnum.CREATED);
            order.setCreatedByUser(currentUser);
            order.setCreated(Calendar.getInstance().getTime());
        }
        order.setLastUpdated(Calendar.getInstance().getTime());
        order.setLastUpdatedByUser(currentUser);
        return super.save(order);
    }

    @Override
    public void deleteOrder(Order order) throws NoPermissionsException {
        User currentUser = userService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        boolean isHasAccess = currentUser.getAccesses().stream().anyMatch(dto -> dto.getAccessLevel() == AccessLevelTypeEnum.DeleteOrder);
        if (isHasAccess)
            super.delete(order);
        else
            throw new NoPermissionsException("Permission for user + " + currentUser.getUsername() + " denied!");

    }

    @Override
    public boolean checkIfCurrentUserHasPermissions(AccessLevelTypeEnum AccessLevelTypeEnum) {
        User currentUser = userService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        return currentUser.getAccesses().stream().anyMatch(dto -> dto.getAccessLevel() == AccessLevelTypeEnum);
    }

    @Override
    public Order updateStatus(Long orderId, String status) {
        Order foundOrder = findById(orderId);
        if (foundOrder != null) {
            foundOrder.setStatus(StatusTypeEnum.valueOf(status));
        }
        return update(foundOrder);
    }

    @Override
    public Order assignDriver(Long orderId, Long driverId) {
        Order foundOrder = findById(orderId);
        User foundDriver = userService.findById(driverId);
        if (foundOrder == null || foundDriver == null) {
            return null;
        }
        foundOrder.setDriver(foundDriver);
        return save(foundOrder);
    }

    @Override
    public Order uploadSignature(Long id, Signature signature) {
        Order order = orderRepository.getOne(id);
        order.setSignaturePicture(signature.getImage());
        order.setSignatureName(signature.getName());
        return save(order);
    }

    @Override
    public Order findOrderByWheelRimPositions(WheelRimPosition wheelRimPosition) {
        return orderRepository.findOrderByWheelRimPositions(wheelRimPosition);
    }

    @Override
    public Order findOrderByOrderNo(String orderNo) {
        return orderRepository.findOrderByOrderNo(orderNo);
    }

    @Override
    public List<Order> fetchOrdersFromPage(Integer pageNumber) {
        return orderRepository.findAll(new PageRequest(pageNumber, 10, Sort.Direction.DESC, "orderNo")).getContent();
    }

    @Override
    public Order findById(Long id) {
        Order order = super.findById(id);
        if (order.getWheelRimPositions().size() != 0) {
            Set<WheelRimPosition> sortedWheelRims = new TreeSet<>(order.getWheelRimPositions());
            order.setWheelRimPositions(sortedWheelRims);
        }
        return order;
    }


}
