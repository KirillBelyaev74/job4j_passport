package ru.job4j.job4j_passport.service.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.job4j.job4j_passport.model.Passport;

@Service
public class KafkaProducerServiceToPassport {

    private KafkaTemplate<String, Passport> kafkaTemplatePassport;

    @Autowired
    public KafkaProducerServiceToPassport(KafkaTemplate<String, Passport> kafkaTemplatePassport) {
        this.kafkaTemplatePassport = kafkaTemplatePassport;
    }

    public void sendSavePassports(Passport passport) {
        kafkaTemplatePassport.send("crudMessage", "сохранен", passport);
    }

    public void sendDeletePassports(Passport passport) {
        kafkaTemplatePassport.send("crudMessage", "удален", passport);
    }
}
