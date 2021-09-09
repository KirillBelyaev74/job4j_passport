package ru.job4j.job4j_passport.service.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaCustomerService {

    @KafkaListener(topics = "messagePassport")
    public void customer(ConsumerRecord<String, String> consumerRecord) {
        System.out.println(consumerRecord.value());
    }
}