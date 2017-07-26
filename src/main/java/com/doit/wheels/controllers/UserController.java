package com.doit.wheels.controllers;

import com.doit.wheels.dao.entities.User;
import com.doit.wheels.services.UserService;
import com.doit.wheels.utils.enums.UserRoleEnum;
import com.doit.wheels.utils.exceptions.NoPermissionsException;
import com.doit.wheels.utils.exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/user")
public class UserController extends AbstractController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<User> getUser(@PathVariable Long id) throws NoSuchElementException {
        User user = userService.findById(id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/username/{username}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) throws NoSuchElementException {
        User user = userService.findUserByUsername(username);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<User> postUser(@RequestBody User user) throws UserException {
        User newUser = userService.addNewUser(user);

        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<User> putUser(@RequestBody User inputUser) {
        User user = userService.updateUser(inputUser);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<User> removeUserWithAccesses(@RequestBody Long idUser) throws NoPermissionsException {

        User user = userService.findById(idUser);
        userService.removeUserWithAccesses(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/drivers", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<User>> getAllDrivers() {
        List<User> users = userService.findAllByRole(UserRoleEnum.DRIVER);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
