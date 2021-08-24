package ru.job4j.job4j_passport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.job4j_passport.exceptionHandler.IllegalSeriesOrNumberException;
import ru.job4j.job4j_passport.model.Passport;
import ru.job4j.job4j_passport.repository.hibernate.PassportRepository;

import java.util.List;

@Service
public class PassportService {

    private final PassportRepository repository;

    @Autowired
    public PassportService(PassportRepository repository) {
        this.repository = repository;
    }

    public Passport saveOrUpdate(Passport passport) {
        if (String.valueOf(passport.getSeries()).length() != 4 || String.valueOf(passport.getNumber()).length() != 6) {
            throw new IllegalSeriesOrNumberException
                    ("Серия паспорта должна состоять из четырех цифр.\n" +
                            "Номер паспорта должен состоять из шести цифр.\n" +
                            "Ваша серия паспорта " + passport.getSeries() + " и ваш номер паспорта " + passport.getNumber());
        }
        return repository.saveOrUpdate(passport);
    }

    public List<Passport> findAll() {
        return repository.findAll();
    }

    public List<Passport> findBySeries(int series) {
        if (String.valueOf(series).length() != 4) {
            throw new IllegalSeriesOrNumberException
                    ("Серия паспорта должна состоять из четырех цифр.\n" +
                            "Вы пытаетесь найти паспорт с серией " + series);
        }
        return repository.findBySeries(series);
    }

    public List<Passport> findUnavailable() {
        return repository.findUnavailable();
    }

    public void deleteById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException
                    ("ID не должен быть меньше или равен нулю. Ваш ID равен = " + id);
        }
        repository.deleteById(id);
    }
}
