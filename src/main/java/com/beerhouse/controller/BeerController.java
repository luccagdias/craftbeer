package com.beerhouse.controller;

import com.beerhouse.model.Beer;
import com.beerhouse.service.BeerService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<String> insertBeer(@RequestBody Beer beer) {
        service.insert(beer);
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteBeer(@PathVariable Long id) {
        boolean deleted = service.delete(id);
        return (deleted) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
