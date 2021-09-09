package ru.job4j.job4j_passport.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.job4j_passport.model.Passport;

import java.util.List;

public interface PassportRepository extends CrudRepository<Passport, Integer> {

    @Override
    List<Passport> findAll();

    Passport findById(int id);

    List<Passport> findBySeries(int series);

    @Query("from Passport where finished < current_timestamp ")
    List<Passport> findByUnavailable();
}
