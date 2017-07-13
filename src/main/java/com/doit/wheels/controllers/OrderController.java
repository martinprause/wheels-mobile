package com.doit.wheels.controllers;

import com.doit.wheels.dao.entities.Order;
import com.doit.wheels.services.OrderService;
import com.doit.wheels.utils.exceptions.NoPermissionsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/order")
public class OrderController extends AbstractController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Order> getUser(@PathVariable Long id) throws NoSuchElementException {
        Order order = orderService.getById(id);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.findAll();

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Order> postOrder(@RequestBody Order order) {
        Order newOrder = orderService.save(order);

        return new ResponseEntity<>(newOrder, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Order> putOrder(@RequestBody Order newOrder) {
        Order order = orderService.update(newOrder);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Order> removeOrderWithAccesses(@RequestBody Long idOrder) throws NoPermissionsException {

        Order order = orderService.getById(idOrder);
        orderService.delete(order);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

}
