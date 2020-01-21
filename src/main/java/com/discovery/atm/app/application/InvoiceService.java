package com.discovery.atm.app.application;

import com.discovery.atm.app.domain.Invoice;
import com.discovery.atm.app.domain.LineItem;
import com.discovery.atm.app.domain.dto.InvoiceRequestDto;
import com.discovery.atm.app.domain.dto.InvoiceResponseDto;
import com.discovery.atm.app.infrastructure.persistence.InvoiceRepository;
import com.discovery.atm.app.infrastructure.persistence.LineItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class InvoiceService {

    private static final Long VAT_RATE = 15L;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private LineItemRepository lineItemRepository;

    /**
     * Method to get an invoice by Id
     * @param id invoice id
     * @return Invoice
     */
    public InvoiceResponseDto getInvoiceById(Long id){
        InvoiceResponseDto response = new InvoiceResponseDto();

     try{
       Optional<Invoice> invoice = invoiceRepository.findById(id);

       if(invoice.isPresent()){
          Invoice inv =  invoice.get();

           response.setId(inv.getId());
           response.setClient(inv.getClient());
           response.setVatRate(inv.getVatRate());
           response.setInvoiceDate(inv.getInvoiceDate().toString());

           List<LineItem> lineItems = getAllLineItems(inv.getId());

           response.setSubTotal(getSubTotal(lineItems));

           response.setVat(getVat(response.getSubTotal(), inv.getVatRate()));
           response.setTotal(getTotal(response.getVat(), response.getSubTotal()));

           response.setLineItems(getAllLineItems(inv.getId()));
       }
       return response;
     }catch (Exception e){
         throw e;
     }
    }

    /**
     * Method to get a list of all line items with invoice id
     * @return list of lineItems
     */
    public List<LineItem> getAllLineItems(Long invoiceId){

        Iterable<LineItem> invoiceLineItems = lineItemRepository.findAllByInvoiceId(invoiceId);

        List<LineItem> lineItems = StreamSupport
                .stream(invoiceLineItems.spliterator(), false)
                .collect(Collectors.toList());

        return lineItems;
    }

    /**
     * Method to get a list of invoices
     * @return list of invoices
     */
    public List<Invoice> getAllInvoices(){

        Iterable<Invoice> allInvoices = invoiceRepository.findAll();

        List<Invoice> responseInvoices = StreamSupport
                .stream(allInvoices.spliterator(), false)
                .collect(Collectors.toList());

        return responseInvoices;
    }

    /**
     * Method to create a new invoice
     * @param requestInvoice the invoice object
     */
    public void saveInvoice(InvoiceRequestDto requestInvoice){

        Invoice invoice = new Invoice();
        invoice.setClient(requestInvoice.getClient());
        invoice.setVatRate(VAT_RATE);
        invoice.setInvoiceDate(new Date());

        invoiceRepository.save(invoice);

        for(LineItem item : requestInvoice.getLineItems()){

            LineItem newItem = new LineItem();
            newItem.setDescription(item.getDescription());
            newItem.setQuantity(item.getQuantity());
            newItem.setUnitPrice(item.getUnitPrice());
        //    newItem.setInvoice(invoice);

            lineItemRepository.save(newItem);

        }

    }

    public BigDecimal getLineItemTotal(long quantity, BigDecimal unitPrice ){

        BigDecimal quantityTotal = new BigDecimal(quantity).multiply(unitPrice);
        return quantityTotal.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getSubTotal(List<LineItem> lineItems){

        try{
            BigDecimal subTotal = new BigDecimal(0);

            for(LineItem item: lineItems) {

                subTotal = subTotal.add(getLineItemTotal(item.getQuantity(), item.getUnitPrice()));

            }
            return subTotal.setScale(2, RoundingMode.HALF_UP);
        }catch(Exception e){
            throw e;
        }
    }

    public BigDecimal getVat(BigDecimal subTotal, Long vatRate ){

        BigDecimal multiplier = new BigDecimal(vatRate.doubleValue()/100);

        BigDecimal value = new BigDecimal(String.valueOf(subTotal.multiply(multiplier)));

        return  value.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getTotal(BigDecimal vat , BigDecimal subtotal){

        BigDecimal totalWithVat = subtotal.add(vat);
        return totalWithVat.setScale(2, RoundingMode.HALF_UP);
    }
}
