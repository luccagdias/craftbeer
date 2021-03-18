package com.beerhouse.controller;

import com.beerhouse.model.Beer;
import com.beerhouse.service.BeerService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.lang.reflect.Field;
import java.net.URI;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<String> insertBeer(@RequestBody Beer beer) {
        Beer insertedBeer = service.insert(beer);

        URI uri = ServletUriComponentsBuilder
                .fromPath("beers/{id}")
                .buildAndExpand(insertedBeer.getId())
                .toUri();

        JSONObject location = new JSONObject();
        location.put("location", uri);

        return ResponseEntity.created(uri).body(location.toString());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteBeer(@PathVariable Long id) {
        boolean deleted = service.delete(id);
        return (deleted) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity alterBeer(@PathVariable Long id, @RequestBody Beer beer) {
        beer.setId(id);

        boolean updated = service.update(beer);
        return (updated) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity partialAlterBeer(@PathVariable Long id, @RequestBody Map<Object, Object> beerFields) {
        Beer beer = service.findById(id);
        if (beer == null) {
            return ResponseEntity.notFound().build();
        }

        beerFields.forEach((fieldName, fieldValue) -> {
            Field field = ReflectionUtils.findField(Beer.class, (String) fieldName);
            field.setAccessible(true);
            ReflectionUtils.setField(field, beer, fieldValue);
        });

        service.partialUpdate(beer);
        return ResponseEntity.ok().build();
    }
}
