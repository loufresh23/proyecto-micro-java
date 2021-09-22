package com.lascencion.mspay.config;


import com.lascencion.mspay.domain.dto.PayDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class KafkaProducerConfig {

    @Value("${custom.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ProducerFactory<String, PayDto> producerFactory() {

        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, PayDto> kafkaTemplate() {

        KafkaTemplate<String, PayDto> kafkaTemplate = new KafkaTemplate<>(producerFactory());

        kafkaTemplate.setProducerListener(new ProducerListener<String, PayDto>() {

            @Override
            public void onSuccess(ProducerRecord<String, PayDto> producerRecord, RecordMetadata recordMetadata) {

                log.info("Success to publish message: {}  offset:  {}", producerRecord.value(),
                        recordMetadata.offset());
            }

            @Override
            public void onError(ProducerRecord<String, PayDto> producerRecord, RecordMetadata recordMetadata,
                                Exception exception) {
                log.warn("Error to publish message [{}] exception: {}", producerRecord.value(), exception.getMessage());
            }
        });
        return kafkaTemplate;
    }
}
