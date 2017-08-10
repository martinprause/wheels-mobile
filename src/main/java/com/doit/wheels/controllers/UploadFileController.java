package com.doit.wheels.controllers;

import com.doit.wheels.dao.entities.Order;
import com.doit.wheels.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/file")
public class UploadFileController extends AbstractController {

    private final StorageService storageService;

    @Autowired
    public UploadFileController(StorageService storageService) {
        this.storageService = storageService;
    }

    @RequestMapping(value = "/wheel-rim", params = {"orderId"}, method = RequestMethod.POST, headers = "content-type=multipart/*")
    @ResponseBody
    public ResponseEntity<Order> handleFileUpload(@RequestParam(required = false) MultipartFile wheel1, @RequestParam(required = false) MultipartFile wheel2,
                                  @RequestParam(required = false) MultipartFile wheel3, @RequestParam(required = false) MultipartFile wheel4,
                                  @RequestParam(value = "orderId") Long orderId) throws IOException {
        Map<String, MultipartFile> wheels = new HashMap<>();
        wheels.put("wheel1", wheel1);
        wheels.put("wheel2", wheel2);
        wheels.put("wheel3", wheel3);
        wheels.put("wheel4", wheel4);
        Order order = storageService.uploadWheelRims(orderId, wheels);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @RequestMapping(value = "/signature/{id}", method = RequestMethod.POST, headers = "content-type=multipart/*")
    @ResponseBody
    public ResponseEntity<Order> uploadSignature(@RequestParam("file") MultipartFile file,
                                                 @RequestParam("name") String name,
                                                 @PathVariable Long id ){
        Order order = storageService.uploadSignature(id, file, name);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }


}
