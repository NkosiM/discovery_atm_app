package com.discovery.atm.app.domain.dto;

import com.discovery.atm.app.domain.LineItem;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class InvoiceRequestDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String client;
    private List<LineItem> lineItems;

}
