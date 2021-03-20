package com.beerhouse.service;

import com.beerhouse.model.Beer;
import com.beerhouse.repository.BeerRepository;
import com.beerhouse.service.exception.BeerNotFoundException;
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

        return beer.orElseThrow(() ->
                new BeerNotFoundException("Não foram encontrados dados para o Id indicado"));
    }

    public List<Beer> findAll() {
        return repository.findAll();
    }

    public Beer insert(Beer beer) {
        return repository.save(beer);
    }

    public void delete(Long id) {
        findById(id);

        repository.deleteById(id);
    }

    public Beer update(Beer beer) {
        findById(beer.getId());

        return repository.save(beer);
    }

    public Beer partialUpdate(Beer beer) {
        findById(beer.getId());

        return repository.save(beer);
    }
}
