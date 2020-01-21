package com.discovery.atm.app.application.util;

/**
 * Just a utils class to help the Invoice entity to make some calculations
 */
public class InvoiceReportHelper {

//    public BigDecimal getLineItemTotal(long quantity, BigDecimal unitPrice ){
//
//        // BigDecimal quant = new BigDecimal(quantity);
//        BigDecimal quantityTotal = new BigDecimal(quantity).multiply(unitPrice);
//        return quantityTotal.setScale(2, RoundingMode.HALF_UP);
//    }
//
//    public BigDecimal getSubTotal(List<LineItem> lineItems){
//
//        try{
//        BigDecimal subTotal = new BigDecimal(0);
//
//        for(LineItem item: lineItems) {
//
//            subTotal = subTotal.add(getLineItemTotal(item.getQuantity(), item.getUnitPrice()));
//
//        }
//        return subTotal.setScale(2, RoundingMode.HALF_UP);
//        }catch(Exception e){
//            throw e;
//        }
//    }
//
//    public BigDecimal getVat(BigDecimal subTotal, Long vatRate ){
//
//        BigDecimal multiplier = new BigDecimal(vatRate/100 + 1);
//
//        BigDecimal vatTotal = subTotal.multiply(multiplier);
//
//        return vatTotal.setScale(2, RoundingMode.HALF_UP);
//    }
//
//    public BigDecimal getTotal(BigDecimal vat , BigDecimal subtotal){
//
//        BigDecimal vatTotalAmount = vat.multiply(subtotal);
//        BigDecimal totalWithVat = subtotal.add(vatTotalAmount);
//        return totalWithVat.setScale(2, RoundingMode.HALF_UP);
//    }
}
