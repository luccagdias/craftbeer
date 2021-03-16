package com.beerhouse.service;

import com.beerhouse.repository.BeerRepository;
import com.beerhouse.model.Beer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeerService {

    @Autowired
    private BeerRepository repository;

    public Beer findById(Long id) {
        Optional<Beer> beer = repository.findById(id);

        return beer.orElse(null);
    }

    public List<Beer> findAll() {
        return repository.findAll();
    }
}
