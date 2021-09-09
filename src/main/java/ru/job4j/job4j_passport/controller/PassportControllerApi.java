package ru.job4j.job4j_passport.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.job4j.job4j_passport.model.Passport;

import java.util.List;

@RestController
@RequestMapping("/passport/api")
public class PassportControllerApi {

    @Value("${url}")
    private String url;
    private final RestTemplate restTemplate;

    public PassportControllerApi(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/")
    public ResponseEntity<Passport> save(@RequestBody Passport passport) {
        passport = restTemplate.postForObject(url, passport, Passport.class);
        return new ResponseEntity<>(passport, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Passport passport) {
        restTemplate.put(url, passport);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) {
        restTemplate.delete(url + "{id}", id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public List getAll() {
        return restTemplate.getForObject(url, List.class);
    }

    @GetMapping("/id/{id}")
    public Passport getById(@PathVariable int id) {
        return restTemplate.getForObject(url + "id/" + id, Passport.class);
    }

    @GetMapping("/{series}")
    public List getBySeries(@PathVariable int series) {
        return restTemplate.getForObject(url + "{series}", List.class, series);
    }

    @GetMapping("/unavailable")
    public List getByUnavailable() {
        return restTemplate.getForObject(url + "unavailable", List.class);
    }
}
