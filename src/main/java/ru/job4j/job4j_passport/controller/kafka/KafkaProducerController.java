package ru.job4j.job4j_passport.controller.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.job4j.job4j_passport.model.Passport;
import ru.job4j.job4j_passport.service.kafka.KafkaProducerService;

import java.util.List;

@EnableScheduling
@Component
public class KafkaProducerController {

    private RestTemplate restTemplate;
    private KafkaProducerService kafkaProducerService;

    @Value("${url}")
    private String url;

    @Autowired
    public KafkaProducerController(RestTemplate restTemplate, KafkaProducerService kafkaProducerService) {
        this.restTemplate = restTemplate;
        this.kafkaProducerService = kafkaProducerService;
    }

    @Scheduled(fixedRate = 100000)
    private void send() {
        List<Passport> passports =
                restTemplate.exchange(url + "unavailable",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Passport>>() {
                        }).getBody();
        if (passports != null) {
            kafkaProducerService.sendUnavailablePassports(passports);
        }
    }
}
