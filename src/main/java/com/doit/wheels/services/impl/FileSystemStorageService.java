package com.doit.wheels.services.impl;

import com.doit.wheels.dao.entities.Order;
import com.doit.wheels.services.OrderService;
import com.doit.wheels.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileSystemStorageService implements StorageService {

    @Autowired
    private OrderService orderService;

    @Override
    public Order store(MultipartFile file, Long orderId) throws IOException {
        if (file.isEmpty()) {
            System.out.println("Failed to store empty file ");
        }

        Order order = orderService.getById(orderId);
        order.setWheelsRimPicture(file.getBytes());
        orderService.update(order);

        return order;
    }

}
