package ru.job4j.job4j_passport.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.job4j.job4j_passport.model.Passport;

import java.util.List;
import java.util.Objects;

@Service
public class KafkaProducerService {

    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendUnavailablePassports(List<Passport> passports) {
        StringBuilder stringBuilder = new StringBuilder();
        passports.stream().filter(Objects::nonNull).forEach(p -> stringBuilder.append(parserMessage(p, " просрочен. Его необходимо заменить.")));
        kafkaTemplate.send("messagePassport", "unavailable", stringBuilder.toString());
    }

    public void sendSavePassports(Passport passport) {
        String resultMessage = parserMessage(passport, " сохранен.");
        kafkaTemplate.send("messagePassport", "save", resultMessage);
    }

    public void sendDeletePassports(Passport passport) {
        String resultMessage = parserMessage(passport, " удален.");
        kafkaTemplate.send("messagePassport", "delete", resultMessage);
    }

    private String parserMessage(Passport p, String message) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("Паспорт пользователя ")
                .append(p.getName())
                .append(" ")
                .append(p.getSurname())
                .append(" ")
                .append(p.getMiddleName())
                .append(message)
                .append("\n");
        return stringBuilder.toString();
    }
}