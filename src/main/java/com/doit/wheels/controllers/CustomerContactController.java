package com.doit.wheels.controllers;

import com.doit.wheels.dao.entities.CustomerContact;
import com.doit.wheels.services.CustomerContactService;
import com.doit.wheels.utils.exceptions.NoPermissionsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/customerContact")
public class CustomerContactController extends AbstractController {

    @Autowired
    private CustomerContactService customerContactService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<CustomerContact> getUser(@PathVariable Long id) throws NoSuchElementException {
        CustomerContact customerContact = customerContactService.findById(id);

        return new ResponseEntity<>(customerContact, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<CustomerContact>> getAllCustomerContacts() {
        List<CustomerContact> customerContacts = customerContactService.findAll();

        return new ResponseEntity<>(customerContacts, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<CustomerContact> postCustomerContact(@RequestBody CustomerContact customerContact) {
        CustomerContact newCustomerContact = customerContactService.save(customerContact);

        return new ResponseEntity<>(newCustomerContact, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<CustomerContact> putCustomerContact(@RequestBody CustomerContact newCustomerContact) {
        CustomerContact customerContact = customerContactService.update(newCustomerContact);

        return new ResponseEntity<>(customerContact, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<CustomerContact> removeCustomerContactWithAccesses(@RequestBody Long idCustomerContact) throws NoPermissionsException {

        CustomerContact customerContact = customerContactService.findById(idCustomerContact);
        customerContactService.delete(customerContact);

        return new ResponseEntity<>(customerContact, HttpStatus.OK);
    }

}
