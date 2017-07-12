package com.doit.wheels.controllers;

import com.doit.wheels.dao.entities.Customer;
import com.doit.wheels.services.CustomerService;
import com.doit.wheels.utils.exceptions.NoPermissionsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController extends AbstractController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Customer> getUser(@PathVariable Long id) throws NoSuchElementException {
        Customer customer = customerService.getById(id);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.findAll();

        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Customer> postCustomer(@RequestBody Customer customer) {
        Customer newCustomer = customerService.save(customer);

        return new ResponseEntity<>(newCustomer, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Customer> putCustomer(@RequestBody Customer newCustomer) {
        Customer customer = customerService.update(newCustomer);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Customer> removeCustomerWithAccesses(@RequestBody Long idCustomer) throws NoPermissionsException {

        Customer customer = customerService.getById(idCustomer);
        customerService.delete(customer);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

}
