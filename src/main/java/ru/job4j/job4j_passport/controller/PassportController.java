package ru.job4j.job4j_passport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.job4j_passport.model.Passport;
import ru.job4j.job4j_passport.service.PassportService;
import ru.job4j.job4j_passport.service.kafka.KafkaProducerService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/passport")
public class PassportController {

    private final PassportService passportService;

    @Autowired
    public PassportController(PassportService passportService) {
        this.passportService = passportService;
    }

    @PostMapping("/")
    public ResponseEntity<Passport> save(@Valid @RequestBody Passport passport) {
        passport = passportService.saveOrUpdate(passport);
        return new ResponseEntity<>(passport, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Passport passport) {
        passportService.saveOrUpdate(passport);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) {
        passportService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public List<Passport> getAll() {
        return passportService.findAll();
    }

    @GetMapping("/id/{id}")
    public Passport getById(@PathVariable int id) {
        return passportService.findById(id);
    }

    @GetMapping("/{series}")
    public List<Passport> getBySeries(@PathVariable int series) {
        return passportService.findBySeries(series);
    }

    @GetMapping("/unavailable")
    public List<Passport> getByUnavailable() {
        return passportService.findUnavailable();
    }
}
