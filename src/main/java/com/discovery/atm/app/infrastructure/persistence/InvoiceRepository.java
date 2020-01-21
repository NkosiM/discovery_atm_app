package com.discovery.atm.app.infrastructure.persistence;

import com.discovery.atm.app.domain.Invoice;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@org.springframework.stereotype.Repository
public interface InvoiceRepository extends CrudRepository<Invoice, String> {

    Optional<Invoice> findById(Long id);


}
