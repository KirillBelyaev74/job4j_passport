package ru.job4j.job4j_passport.service.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.job4j.job4j_passport.model.Passport;

import java.util.List;
import java.util.Objects;

@Service
public class KafkaProducerServiceToString {

    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaProducerServiceToString(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendUnavailablePassports(List<Passport> passports) {
        StringBuilder stringBuilder = new StringBuilder();
        passports.stream().filter(Objects::nonNull).forEach(p -> stringBuilder.append(parserMessage(p, " просрочен. Его необходимо заменить.")));
        kafkaTemplate.send("unavailablePassports", "unavailable", stringBuilder.toString());
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