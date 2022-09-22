package model;

import java.util.ArrayList;

public class InvoiceHeader {

    //definition of invoice header data
    String invoiceNum;
    String invoiceDate;
    String customerName;
    ArrayList<InvoiceLine> invoiceLines;

    //constructor for invoiceHeader model
    public InvoiceHeader(String invoiceNum, String invoiceDate, String customerName) {
        this.invoiceNum = invoiceNum;
        this.invoiceDate = invoiceDate;
        this.customerName = customerName;
    }

    //property to get updated data of invoiceHeader
    public String getInvoiceNum() {
        return invoiceNum;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public ArrayList<InvoiceLine> getInvoiceLines() {
        return invoiceLines;
    }
}
