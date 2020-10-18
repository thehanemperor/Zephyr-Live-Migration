package com.example.demo.controller;

import com.example.demo.entity.City;
import com.example.demo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
public class CityController{
    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public ResponseEntity<?>save(@RequestBody City city){
        System.out.println("insert to db "+city.getName());
        cityService.saveandFlush(city);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public  ResponseEntity<?> getAll() throws SQLException{
        List<City> cities = cityService.getAll();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }
    @RequestMapping(path = "/id/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> get(@PathVariable("id") Long id) {
        City city = cityService.get(id);
        String name = city.getName();
        return new ResponseEntity<>(name, HttpStatus.OK);
    }

//    Example of transactions

    @RequestMapping(path = "/id/updateCityName/{id}/{newName}",method = RequestMethod.POST)
    public ResponseEntity<?> update(@PathVariable("id")Long id, @PathVariable("newName")String newName) throws  SQLException{
        System.out.println("updating transaction");
        cityService.updateCityNameTransaction(id,newName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public ResponseEntity<City> get(@PathVariable(value = "name") String name) {
        City city = cityService.getByName(name);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.DELETE)
    public ResponseEntity<City> delete(@PathVariable(value = "name") String name) {
        cityService.delete(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}