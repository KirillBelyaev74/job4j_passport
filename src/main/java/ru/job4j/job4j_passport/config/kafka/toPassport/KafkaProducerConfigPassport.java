package ru.job4j.job4j_passport.config.kafka.toPassport;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.support.serializer.JsonSerializer;
import ru.job4j.job4j_passport.model.Passport;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfigPassport {

    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaServer;

    @Value("${spring.kafka.consumer.group-id-passport}")
    private String kafkaGroupId;

    @Bean
    public ProducerFactory<String, Passport> producerFactoryPassport() {
        return new DefaultKafkaProducerFactory<>(producerConfigsPassport());
    }

    @Bean
    public KafkaTemplate<String, Passport> kafkaTemplatePassport() {
        KafkaTemplate<String, Passport> template = new KafkaTemplate<>(producerFactoryPassport());
        template.setMessageConverter(new StringJsonMessageConverter());
        return template;
    }

    @Bean
    public Map<String, Object> producerConfigsPassport() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, kafkaGroupId);
        return props;
    }
}
