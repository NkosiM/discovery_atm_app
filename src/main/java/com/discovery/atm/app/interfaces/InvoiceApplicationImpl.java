package com.discovery.atm.app.interfaces;

import com.discovery.atm.app.application.InvoiceService;
import com.discovery.atm.app.domain.Invoice;
import com.discovery.atm.app.domain.dto.InvoiceRequestDto;
import com.discovery.atm.app.domain.dto.InvoiceResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class InvoiceApplicationImpl {

    @Autowired
    private  InvoiceService invoiceService;

    @GetMapping(value = "/invoices/{invoiceId}")
    public InvoiceResponseDto viewInvoice(@PathVariable("invoiceId") Long invoiceId) {
        InvoiceResponseDto invoice = invoiceService.getInvoiceById(invoiceId);
        return invoice;
    }

    @GetMapping(value = "/invoices" )
    public List<Invoice> viewAllInvoices() {
        List<Invoice> allInvoices = invoiceService.getAllInvoices();
        return allInvoices;
    }

    @PostMapping(value = "/invoices")
    public String createNewInvoice(@RequestBody InvoiceRequestDto invoice){

        invoiceService.saveInvoice(invoice);
        return "Invoice for : "+invoice.getClient()+" created successfully";
    }

}
