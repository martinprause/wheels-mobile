package com.doit.wheels.controllers;

import com.doit.wheels.dao.entities.Order;
import com.doit.wheels.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/pictureWheelsRim")
public class PictureWheelsRimController {

    @Autowired
    private StorageService storageService;

    @RequestMapping(params = {"orderId"}, method = RequestMethod.POST, headers = "content-type=multipart/*")
    @ResponseBody
    public Order handleFileUpload(@RequestParam("file") MultipartFile file,
                                  @RequestParam(value = "orderId") Long orderId) throws IOException {

        Order order = storageService.store(file, orderId);

        return order;
    }

}
