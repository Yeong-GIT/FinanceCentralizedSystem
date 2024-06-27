package FinanceSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SalesInvoiceKafkaProducerService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendTestMessage() {
        String topic = "sales-invoice-topic";
        String message = "{\"customerName\": \"Jane Doe\", \"amount\": \"200.75\", \"invoiceDate\": \"2023-06-26\"}";
        kafkaTemplate.send(topic, message);
    }
}
