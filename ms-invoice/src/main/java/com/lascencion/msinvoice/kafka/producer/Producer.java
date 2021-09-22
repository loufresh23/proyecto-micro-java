package com.lascencion.msinvoice.kafka.producer;

import com.lascencion.msinvoice.domain.dto.InvoiceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    @Value("${custom.kafka.topic-name-invoice}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, InvoiceDto> kafkaTemplate;

    public void producer(InvoiceDto messageDTO) {
        kafkaTemplate.send(topicName, messageDTO);
    }
}
