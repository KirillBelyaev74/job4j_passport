package ru.job4j.job4j_passport.config.kafka.toPassport;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import ru.job4j.job4j_passport.model.Passport;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfigPassport {

    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaServer;

    @Value("${spring.kafka.consumer.group-id-passport}")
    private String kafkaGroupId;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Passport> kafkaListenerContainerFactoryPassport() {
        ConcurrentKafkaListenerContainerFactory<String, Passport> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactoryPassport());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, Passport> consumerFactoryPassport() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigsPassport(), new StringDeserializer(), new JsonDeserializer<>(Passport.class));
    }

    @Bean
    public Map<String, Object> consumerConfigsPassport() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaGroupId);
        return props;
    }
}
