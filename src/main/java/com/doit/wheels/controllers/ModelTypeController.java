package com.doit.wheels.controllers;

import com.doit.wheels.dao.entities.ModelType;
import com.doit.wheels.services.ModelTypeService;
import com.doit.wheels.utils.exceptions.NoPermissionsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/modelType")
public class ModelTypeController extends AbstractController {

    @Autowired
    private ModelTypeService modelTypeService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ModelType> getUser(@PathVariable Long id) throws NoSuchElementException {
        ModelType modelType = modelTypeService.getById(id);

        return new ResponseEntity<>(modelType, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<ModelType>> getAllModelTypes() {
        List<ModelType> modelTypes = modelTypeService.findAll();

        return new ResponseEntity<>(modelTypes, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ModelType> postModelType(@RequestBody ModelType modelType) {
        ModelType newModelType = modelTypeService.save(modelType);

        return new ResponseEntity<>(newModelType, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<ModelType> putModelType(@RequestBody ModelType newModelType) {
        ModelType modelType = modelTypeService.update(newModelType);

        return new ResponseEntity<>(modelType, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ModelType> removeModelTypeWithAccesses(@RequestBody Long idModelType) throws NoPermissionsException {

        ModelType modelType = modelTypeService.getById(idModelType);
        modelTypeService.delete(modelType);

        return new ResponseEntity<>(modelType, HttpStatus.OK);
    }

}
