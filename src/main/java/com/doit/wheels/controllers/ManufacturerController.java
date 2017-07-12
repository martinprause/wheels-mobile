package com.doit.wheels.controllers;

import com.doit.wheels.dao.entities.Manufacturer;
import com.doit.wheels.services.ManufacturerService;
import com.doit.wheels.utils.exceptions.NoPermissionsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/manufacturer")
public class ManufacturerController extends AbstractController {

    @Autowired
    private ManufacturerService manufacturerService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Manufacturer> getUser(@PathVariable Long id) throws NoSuchElementException {
        Manufacturer manufacturer = manufacturerService.getById(id);

        return new ResponseEntity<>(manufacturer, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Manufacturer>> getAllManufacturers() {
        List<Manufacturer> manufacturers = manufacturerService.findAll();

        return new ResponseEntity<>(manufacturers, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Manufacturer> postManufacturer(@RequestBody Manufacturer manufacturer) {
        Manufacturer newManufacturer = manufacturerService.save(manufacturer);

        return new ResponseEntity<>(newManufacturer, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Manufacturer> putManufacturer(@RequestBody Manufacturer newManufacturer) {
        Manufacturer manufacturer = manufacturerService.update(newManufacturer);

        return new ResponseEntity<>(manufacturer, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Manufacturer> removeManufacturerWithAccesses(@RequestBody Long idManufacturer) throws NoPermissionsException {

        Manufacturer manufacturer = manufacturerService.getById(idManufacturer);
        manufacturerService.delete(manufacturer);

        return new ResponseEntity<>(manufacturer, HttpStatus.OK);
    }

}
