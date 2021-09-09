package ru.job4j.job4j_passport.validate;

import org.springframework.stereotype.Component;
import ru.job4j.job4j_passport.exceptionHandler.IllegalSeriesOrNumberException;
import ru.job4j.job4j_passport.model.Passport;

@Component
public class PassportValidate {

    public void validatePassport(Passport passport) {
        if (String.valueOf(passport.getSeries()).length() != 4 || String.valueOf(passport.getNumber()).length() != 6) {
            throw new IllegalSeriesOrNumberException
                    ("Серия паспорта должна состоять из четырех цифр.\n" +
                            "Номер паспорта должен состоять из шести цифр.\n" +
                            "Ваша серия паспорта " + passport.getSeries() + " и ваш номер паспорта " + passport.getNumber());
        }
    }

    public void validateId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException
                    ("ID не должен быть меньше или равен нулю. Ваш ID равен = " + id);
        }
    }

    public void validateSeries(int series) {
        if (String.valueOf(series).length() != 4) {
            throw new IllegalSeriesOrNumberException
                    ("Серия паспорта должна состоять из четырех цифр.\n" +
                            "Вы пытаетесь найти паспорт с серией " + series);
        }
    }
}
