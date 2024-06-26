package FinanceSystem.service;

import FinanceSystem.entity.CashReceipt;
import FinanceSystem.entity.SalesInvoice;
import FinanceSystem.repository.CashReceiptRepository;
import FinanceSystem.repository.SalesInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class FinanceKafkaConsumerService {

    @Autowired
    private CashReceiptRepository cashReceiptRepository;

    @Autowired
    private SalesInvoiceRepository salesInvoiceRepository;

    @KafkaListener(topics = "cash-receipt-topic", groupId = "group_id")
    public void consumeCashReceipt(String message) {
        // Deserialize the message to CashReceipt entity
        CashReceipt cashReceipt = parseCashReceipt(message);
        cashReceiptRepository.save(cashReceipt);
        System.out.println("Consumed and saved cash receipt: " + cashReceipt);
    }

    @KafkaListener(topics = "sales-invoice-topic", groupId = "group_id")
    public void consumeSalesInvoice(String message) {
        // Deserialize the message to SalesInvoice entity
        SalesInvoice salesInvoice = parseSalesInvoice(message);
        salesInvoiceRepository.save(salesInvoice);
        System.out.println("Consumed and saved sales invoice: " + salesInvoice);
    }

    private CashReceipt parseCashReceipt(String message) {
        // Implement the logic to parse the message to a CashReceipt entity
        // For simplicity, using hardcoded values
        CashReceipt receipt = new CashReceipt();
        receipt.setCustomerName("Dummy Customer");
        receipt.setAmount(new BigDecimal("100.50"));
        receipt.setReceiptDate(LocalDate.now());
        return receipt;
    }

    private SalesInvoice parseSalesInvoice(String message) {
        // Implement the logic to parse the message to a SalesInvoice entity
        // For simplicity, using hardcoded values
        SalesInvoice invoice = new SalesInvoice();
        invoice.setCustomerName("Dummy Customer");
        invoice.setAmount(new BigDecimal("200.75"));
        invoice.setInvoiceDate(LocalDate.now());
        return invoice;
    }
}
