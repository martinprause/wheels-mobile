package com.doit.wheels.controllers;

import com.doit.wheels.dao.entities.Country;
import com.doit.wheels.services.CountryService;
import com.doit.wheels.utils.exceptions.NoPermissionsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/country")
public class CountryController extends AbstractController {

    @Autowired
    private CountryService countryService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Country> getUser(@PathVariable Long id) throws NoSuchElementException {
        Country country = countryService.getById(id);

        return new ResponseEntity<>(country, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Country>> getAllCountrys() {
        List<Country> countries = countryService.findAll();

        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Country> postCountry(@RequestBody Country country) {
        Country newCountry = countryService.save(country);

        return new ResponseEntity<>(newCountry, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Country> putCountry(@RequestBody Country newCountry) {
        Country country = countryService.update(newCountry);

        return new ResponseEntity<>(country, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Country> removeCountryWithAccesses(@RequestBody Long idCountry) throws NoPermissionsException {

        Country country = countryService.getById(idCountry);
        countryService.delete(country);

        return new ResponseEntity<>(country, HttpStatus.OK);
    }

}
