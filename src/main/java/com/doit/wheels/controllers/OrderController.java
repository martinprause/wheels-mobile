package com.doit.wheels.controllers;

import com.doit.wheels.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/order")
public class OrderController extends AbstractController {

    @Autowired
    private OrderService orderService;



}
