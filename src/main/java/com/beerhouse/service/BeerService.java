package com.beerhouse.service;

import com.beerhouse.model.Beer;
import com.beerhouse.repository.BeerRepository;
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

    public Beer insert(Beer beer) {
        return repository.save(beer);
    }

    public boolean delete(Long id) {
        if (findById(id) == null) {
            return false;
        }

        repository.deleteById(id);
        return true;
    }

    public boolean update(Beer beer) {
        if (findById(beer.getId()) == null) {
            return false;
        }

        repository.save(beer);
        return true;
    }

    public void partialUpdate(Beer beer) {
        repository.save(beer);
    }
}
