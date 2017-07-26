package com.doit.wheels.controllers;

import com.doit.wheels.dao.entities.ValveType;
import com.doit.wheels.services.ValveTypeService;
import com.doit.wheels.utils.exceptions.NoPermissionsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/valveType")
public class ValveTypeController extends AbstractController {

    @Autowired
    private ValveTypeService valveTypeService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ValveType> getUser(@PathVariable Long id) throws NoSuchElementException {
        ValveType valveType = valveTypeService.findById(id);

        return new ResponseEntity<>(valveType, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<ValveType>> getAllValveTypes() {
        List<ValveType> valveTypes = valveTypeService.findAll();

        return new ResponseEntity<>(valveTypes, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ValveType> postValveType(@RequestBody ValveType valveType) {
        ValveType newValveType = valveTypeService.save(valveType);

        return new ResponseEntity<>(newValveType, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<ValveType> putValveType(@RequestBody ValveType newValveType) {
        ValveType valveType = valveTypeService.update(newValveType);

        return new ResponseEntity<>(valveType, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ValveType> removeValveTypeWithAccesses(@RequestBody Long idValveType) throws NoPermissionsException {

        ValveType valveType = valveTypeService.findById(idValveType);
        valveTypeService.delete(valveType);

        return new ResponseEntity<>(valveType, HttpStatus.OK);
    }

}
