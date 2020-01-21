package com.discovery.atm.app.domain.dto;

import com.discovery.atm.app.domain.LineItem;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class InvoiceResponseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String client;
    private Long vatRate;
    private String invoiceDate;
    private BigDecimal total;
    private BigDecimal subTotal;
    private BigDecimal vat;
    private List<LineItem> lineItems;
}
