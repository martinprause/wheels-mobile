package com.doit.wheels.controllers;

import com.doit.wheels.dao.entities.Model;
import com.doit.wheels.services.ModelService;
import com.doit.wheels.utils.exceptions.NoPermissionsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/model")
public class ModelController extends AbstractController {

    @Autowired
    private ModelService modelService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Model> getUser(@PathVariable Long id) throws NoSuchElementException {
        Model model = modelService.findById(id);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Model>> getAllModels() {
        List<Model> models = modelService.findAll();

        return new ResponseEntity<>(models, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Model> postModel(@RequestBody Model model) {
        Model newModel = modelService.save(model);

        return new ResponseEntity<>(newModel, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Model> putModel(@RequestBody Model newModel) {
        Model model = modelService.update(newModel);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Model> removeModelWithAccesses(@RequestBody Long idModel) throws NoPermissionsException {

        Model model = modelService.findById(idModel);
        modelService.delete(model);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

}
