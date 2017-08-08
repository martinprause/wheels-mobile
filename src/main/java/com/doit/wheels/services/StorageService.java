package com.doit.wheels.services;

import com.doit.wheels.dao.entities.Order;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface StorageService {

    Order uploadSignature(Long id, MultipartFile file, String name);

    Order uploadWheelRims(Long id, Map<String,MultipartFile> wheels);

}
