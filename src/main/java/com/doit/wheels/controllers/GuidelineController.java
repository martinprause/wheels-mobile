package com.doit.wheels.controllers;

import com.doit.wheels.dao.entities.Guideline;
import com.doit.wheels.services.GuidelineService;
import com.doit.wheels.utils.exceptions.NoPermissionsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/guideline")
public class GuidelineController extends AbstractController {

    @Autowired
    private GuidelineService guidelineService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Guideline> getUser(@PathVariable Long id) throws NoSuchElementException {
        Guideline guideline = guidelineService.getById(id);

        return new ResponseEntity<>(guideline, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Guideline>> getAllGuidelines() {
        List<Guideline> guidelines = guidelineService.findAll();

        return new ResponseEntity<>(guidelines, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Guideline> postGuideline(@RequestBody Guideline guideline) {
        Guideline newGuideline = guidelineService.save(guideline);

        return new ResponseEntity<>(newGuideline, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Guideline> putGuideline(@RequestBody Guideline newGuideline) {
        Guideline guideline = guidelineService.update(newGuideline);

        return new ResponseEntity<>(guideline, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Guideline> removeGuidelineWithAccesses(@RequestBody Long idGuideline) throws NoPermissionsException {

        Guideline guideline = guidelineService.getById(idGuideline);
        guidelineService.delete(guideline);

        return new ResponseEntity<>(guideline, HttpStatus.OK);
    }

}
