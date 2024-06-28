package FinanceSystem.service;

import FinanceSystem.entity.CashReceipt;
import FinanceSystem.entity.SalesInvoice;
import FinanceSystem.repository.CashReceiptRepository;
import FinanceSystem.repository.SalesInvoiceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FinanceKafkaConsumerService {

    @Autowired
    private CashReceiptRepository cashReceiptRepository;

    @Autowired
    private SalesInvoiceRepository salesInvoiceRepository;

    private ObjectMapper objectMapper;

    public FinanceKafkaConsumerService() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @KafkaListener(topics = "cash-receipt-topic", groupId = "group_id")
    public void consumeCashReceipt(String message) {
        try {
            // Deserialize the message to CashReceipt entity
            CashReceipt cashReceipt = objectMapper.readValue(message, CashReceipt.class);
            cashReceiptRepository.save(cashReceipt);
            System.out.println("Consumed and saved cash receipt: " + cashReceipt);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error deserializing cash receipt message: " + message);
        }
    }

    @KafkaListener(topics = "sales-invoice-topic", groupId = "group_id")
    public void consumeSalesInvoice(String message) {
        try {
            // Deserialize the message to SalesInvoice entity
            SalesInvoice salesInvoice = objectMapper.readValue(message, SalesInvoice.class);
            salesInvoiceRepository.save(salesInvoice);
            System.out.println("Consumed and saved sales invoice: " + salesInvoice);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error deserializing sales invoice message: " + message);
        }
    }
}
