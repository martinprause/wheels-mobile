package com.doit.wheels.services.impl;

import com.doit.wheels.dao.entities.Order;
import com.doit.wheels.services.OrderService;
import com.doit.wheels.services.StorageService;
import com.doit.wheels.utils.enums.StatusTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class FileSystemStorageService implements StorageService {

    private final OrderService orderService;

    @Autowired
    public FileSystemStorageService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public Order uploadSignature(Long id, MultipartFile file, String name) {
        if (file.isEmpty()) {
            System.out.println("Failed to store empty file ");
        }

        Order order = orderService.findById(id);
        order.setSignatureName(name);
        try {
            order.setSignaturePicture(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        order.setStatus(StatusTypeEnum.DELIVERED);
        return orderService.update(order);
    }

    @Override
    public Order uploadWheelRims(Long id, Map<String,MultipartFile> wheels) {
        Order order = orderService.findById(id);
        try {
            order.setWheelsRimPicture1(wheels.get("wheel1") == null ? null : wheels.get("wheel1").getBytes());
            order.setWheelsRimPicture2(wheels.get("wheel2") == null ? null : wheels.get("wheel2").getBytes());
            order.setWheelsRimPicture3(wheels.get("wheel3") == null ? null : wheels.get("wheel3").getBytes());
            order.setWheelsRimPicture4(wheels.get("wheel4") == null ? null : wheels.get("wheel4").getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return orderService.update(order);
    }

}
