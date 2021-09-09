package ru.job4j.job4j_passport.service.kafka.customer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.job4j.job4j_passport.model.Passport;

@Service
public class KafkaCustomerService {

    @KafkaListener(groupId = "${spring.kafka.consumer.group-id}",
            topics = "unavailablePassports",
            clientIdPrefix = "unavailable",
            containerFactory = "kafkaListenerContainerFactory")
    public void customer(ConsumerRecord<String, String> consumerRecord) {
        System.out.println(consumerRecord.value());
    }

    @KafkaListener(groupId = "${spring.kafka.consumer.group-id-passport}",
            topics = "crudMessage",
            clientIdPrefix = "crud",
            containerFactory = "kafkaListenerContainerFactoryPassport")
    public void customerPassport(ConsumerRecord<String, Passport> consumerRecord) {
        String key = consumerRecord.key();
        Passport passport = consumerRecord.value();
        System.out.println(
                "Пользователь "
                        + passport.getName() + " "
                        + passport.getSurname() + " "
                        + passport.getMiddleName() + " "
                        + key);
    }
}