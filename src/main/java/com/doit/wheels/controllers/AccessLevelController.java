package com.doit.wheels.controllers;

import com.doit.wheels.dao.entities.AccessLevel;
import com.doit.wheels.services.AccessLevelService;
import com.doit.wheels.utils.enums.AccessLevelTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/accessLevel")
public class AccessLevelController extends AbstractController {

    @Autowired
    private AccessLevelService accessLevelService;

    @RequestMapping(value = "/{accessLevelType}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<AccessLevel> getAccessLevel(@PathVariable String accessLevelType) throws NoSuchElementException {
        AccessLevel accessLevel = accessLevelService.findAccessLevelByAccessLevel(AccessLevelTypeEnum.valueOf(accessLevelType));

        return new ResponseEntity<>(accessLevel, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<AccessLevel> postAccessLevel(@RequestBody AccessLevel accessLevel) {
        AccessLevel newAccessLevel = accessLevelService.save(accessLevel);

        return new ResponseEntity<>(newAccessLevel, HttpStatus.OK);
    }

}
