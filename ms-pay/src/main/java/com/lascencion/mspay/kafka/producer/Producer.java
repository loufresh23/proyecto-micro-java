package com.lascencion.mspay.kafka.producer;

import com.lascencion.mspay.domain.dto.PayDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    @Value("${custom.kafka.topic-name-pay}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, PayDto> kafkaTemplate;

    public void producer(PayDto messageDTO) {
        kafkaTemplate.send(topicName, messageDTO);
    }
}
