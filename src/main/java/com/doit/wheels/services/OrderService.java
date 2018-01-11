package com.doit.wheels.services;

import com.doit.wheels.dao.entities.Order;
import com.doit.wheels.dao.entities.PicturesDto;
import com.doit.wheels.dao.entities.Signature;
import com.doit.wheels.dao.entities.WheelRimPosition;
import com.doit.wheels.utils.enums.AccessLevelTypeEnum;
import com.doit.wheels.utils.exceptions.NoPermissionsException;

import java.util.List;

public interface OrderService  extends GenericService<Order> {

    Order findOrderByOrderNo(String orderNo);

    List<Order> fetchOrdersFromPage(Integer pageNumber);

    void deleteOrder(Order order) throws NoPermissionsException;

    boolean checkIfCurrentUserHasPermissions(AccessLevelTypeEnum accessLevelTypeEnum);

    Order updateStatus(Long orderId, String status);

    Order assignDriver(Long orderId, Long driverId);

    Order uploadSignature(Long id, Signature signature);

    Order findOrderByWheelRimPositions(WheelRimPosition wheelRimPosition);

    PicturesDto getWheelsPicturesByOrderNo(String orderNo);
}
