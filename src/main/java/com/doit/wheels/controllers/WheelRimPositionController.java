package com.doit.wheels.controllers;

import com.doit.wheels.dao.entities.WheelRimPosition;
import com.doit.wheels.services.WheelRimPositionService;
import com.doit.wheels.utils.exceptions.NoPermissionsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/wheelRimPosition")
public class WheelRimPositionController extends AbstractController {

    private final WheelRimPositionService wheelRimPositionService;

    @Autowired
    public WheelRimPositionController(WheelRimPositionService wheelRimPositionService) {
        this.wheelRimPositionService = wheelRimPositionService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<WheelRimPosition> getUser(@PathVariable Long id) throws NoSuchElementException {
        WheelRimPosition wheelRimPosition = wheelRimPositionService.findById(id);

        return new ResponseEntity<>(wheelRimPosition, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<WheelRimPosition>> getAllWheelRimPositions() {
        List<WheelRimPosition> wheelRimPositions = wheelRimPositionService.findAll();

        return new ResponseEntity<>(wheelRimPositions, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<WheelRimPosition> postWheelRimPosition(@RequestBody WheelRimPosition wheelRimPosition) {
        WheelRimPosition newWheelRimPosition = wheelRimPositionService.save(wheelRimPosition);

        return new ResponseEntity<>(newWheelRimPosition, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<WheelRimPosition> putWheelRimPosition(@RequestBody WheelRimPosition newWheelRimPosition) {
        WheelRimPosition wheelRimPosition = wheelRimPositionService.update(newWheelRimPosition);

        return new ResponseEntity<>(wheelRimPosition, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<WheelRimPosition> removeWheelRimPositionWithAccesses(@RequestBody Long idWheelRimPosition) throws NoPermissionsException {

        WheelRimPosition wheelRimPosition = wheelRimPositionService.findById(idWheelRimPosition);
        wheelRimPositionService.delete(wheelRimPosition);

        return new ResponseEntity<>(wheelRimPosition, HttpStatus.OK);
    }

}
