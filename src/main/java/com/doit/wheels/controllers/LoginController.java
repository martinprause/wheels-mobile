package com.doit.wheels.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController extends AbstractController{

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity verifyLogin() {
        return new ResponseEntity(HttpStatus.OK);
    }
}
