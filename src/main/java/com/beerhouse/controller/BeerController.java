package com.beerhouse.controller;

import com.beerhouse.model.Beer;
import com.beerhouse.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/beers")
public class BeerController {

    @Autowired
    private BeerService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Beer> getBeer(@PathVariable Long id) {
        Beer beer = service.findById(id);
        return ResponseEntity.ok().body(beer);
    }

    @GetMapping()
    public ResponseEntity<List<Beer>> getAllBeers() {
        List<Beer> beers = service.findAll();
        return ResponseEntity.ok().body(beers);
    }
}
