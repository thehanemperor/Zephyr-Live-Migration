package com.example.demo.service;
import com.example.demo.entity.City;
import com.example.demo.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public void saveandFlush(City city){
        cityRepository.saveAndFlush(city);
    }

    public List<City> getAll() throws SQLException{
        return cityRepository.findAll();
    }
    public City get(Long id){
        return cityRepository.getOne(id);
    }

    public City getByName(String name){
        return cityRepository.findByName(name);
    }

    public void delete(String name){
        cityRepository.deleteByName(name);
    }

    // examples for a transactional operation
    @Transactional
    public void updateCityNameTransaction(Long id,String newName) {
        City city = cityRepository.getOne(id);

        city.setName(newName);
    }
}
