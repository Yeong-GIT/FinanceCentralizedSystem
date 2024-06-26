package FinanceSystem.repository;

import FinanceSystem.entity.SalesInvoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesInvoiceRepository extends JpaRepository<SalesInvoice, Long> {
}
