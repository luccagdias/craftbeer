package com.beerhouse.controller;

import com.beerhouse.model.Beer;
import com.beerhouse.service.BeerService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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

    @PostMapping(produces = "application/json")
    public ResponseEntity<String> insertBeer(@RequestBody @Valid Beer beer) {
        Beer insertedBeer = service.insert(beer);

        URI uri = ServletUriComponentsBuilder
                .fromPath("/beers/{id}")
                .buildAndExpand(insertedBeer.getId())
                .toUri();

        JSONObject location = new JSONObject();
        location.put("location", uri);

        return ResponseEntity.created(uri).body(location.toString());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteBeer(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity alterBeer(@PathVariable Long id, @RequestBody @Valid Beer beer) {
        beer.setId(id);

        service.update(beer);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity partialAlterBeer(@PathVariable Long id, @RequestBody @Valid Beer beer) {
        beer.setId(id);

        service.update(beer);
        return ResponseEntity.ok().build();
    }
}
