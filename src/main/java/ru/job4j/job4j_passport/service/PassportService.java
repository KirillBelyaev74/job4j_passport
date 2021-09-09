package ru.job4j.job4j_passport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.job4j_passport.model.Passport;
import ru.job4j.job4j_passport.repository.PassportRepository;
import ru.job4j.job4j_passport.service.kafka.producer.KafkaProducerServiceToPassport;
import ru.job4j.job4j_passport.service.kafka.producer.KafkaProducerServiceToString;
import ru.job4j.job4j_passport.validate.PassportValidate;

import java.util.List;

@Service
public class PassportService {

    private final PassportRepository repository;
    private final PassportValidate passportValidate;
    private final KafkaProducerServiceToPassport kafkaProducerService;

    @Autowired
    public PassportService(PassportRepository repository, PassportValidate passportValidate, KafkaProducerServiceToPassport kafkaProducerService) {
        this.repository = repository;
        this.passportValidate = passportValidate;
        this.kafkaProducerService = kafkaProducerService;
    }

    public Passport saveOrUpdate(Passport passport) {
        passportValidate.validatePassport(passport);
        passport = repository.save(passport);
        kafkaProducerService.sendSavePassports(passport);
        return passport;
    }

    public List<Passport> findAll() {
        return repository.findAll();
    }

    public Passport findById(int id) {
        passportValidate.validateId(id);
        return repository.findById(id);
    }

    public List<Passport> findBySeries(int series) {
        passportValidate.validateSeries(series);
        return repository.findBySeries(series);
    }

    public List<Passport> findUnavailable() {
        return repository.findByUnavailable();
    }

    public void deleteById(int id) {
        passportValidate.validateId(id);
        Passport passport = findById(id);
        kafkaProducerService.sendDeletePassports(passport);
        repository.deleteById(id);
    }
}
