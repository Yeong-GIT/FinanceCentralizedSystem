package FinanceSystem.repository;

import FinanceSystem.entity.CashReceipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashReceiptRepository extends JpaRepository<CashReceipt, Long> {
}
