package com.discovery.atm.app.infrastructure.persistence;

import com.discovery.atm.app.domain.LineItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineItemRepository extends CrudRepository<LineItem, String> {

    Iterable<LineItem> findAllByInvoiceId(Long invoiceId);
}
