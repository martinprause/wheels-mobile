package com.doit.wheels.services;

import com.doit.wheels.dao.entities.Order;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {

    Order store(MultipartFile file, Long orderId) throws IOException;

}
