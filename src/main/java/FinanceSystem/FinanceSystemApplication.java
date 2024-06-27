package FinanceSystem;

import FinanceSystem.service.CashReceiptKafkaProducerService;
import FinanceSystem.service.SalesInvoiceKafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class FinanceSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceSystemApplication.class, args);
	}
}

@RestController
class TestController {

	@Autowired
	private CashReceiptKafkaProducerService cashReceiptKafkaProducerService;

	@Autowired
	private SalesInvoiceKafkaProducerService salesInvoiceKafkaProducerService;

	@GetMapping("/sendCashReceiptTestMessage")
	public String sendCashReceiptTestMessage() {
		cashReceiptKafkaProducerService.sendTestMessage();
		return "Cash receipt test message sent";
	}

	@GetMapping("/sendSalesInvoiceTestMessage")
	public String sendSalesInvoiceTestMessage() {
		salesInvoiceKafkaProducerService.sendTestMessage();
		return "Sales invoice test message sent";
	}
}
