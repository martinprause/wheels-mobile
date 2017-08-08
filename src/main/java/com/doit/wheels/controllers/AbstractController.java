package com.doit.wheels.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600,
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS, RequestMethod.DELETE, RequestMethod.PUT},
        allowedHeaders = {"x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN, form-data"})
@RestController
@RequestMapping(value = "/**")
public abstract class AbstractController {

    @RequestMapping(method = RequestMethod.OPTIONS)
    @ResponseBody
    public ResponseEntity handle() {
        return new ResponseEntity(HttpStatus.OK);
    }

}
