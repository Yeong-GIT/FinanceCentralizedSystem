package FinanceSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CashReceiptKafkaProducerService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendTestMessage() {
        String topic = "cash-receipt-topic";
        String message = "{\"customerName\": \"John Doe\", \"amount\": \"100.50\", \"receiptDate\": \"2023-06-26\"}";
        kafkaTemplate.send(topic, message);
    }
}
