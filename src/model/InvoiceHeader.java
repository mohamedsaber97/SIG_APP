package model;

import java.util.ArrayList;

public class InvoiceHeader {

    //definition of invoice header data
    String invoiceNum;
    String invoiceDate;
    String customerName;
    ArrayList<InvoiceLine> invoiceLines;

    double total = 0.0;

    //create default constructor
    public InvoiceHeader() {
    }

    //constructor for invoiceHeader model
    public InvoiceHeader(String invoiceNum, String invoiceDate, String customerName) {
        this.invoiceNum = invoiceNum;
        this.invoiceDate = invoiceDate;
        this.customerName = customerName;
    }

    //properties to get updated data of invoiceHeader
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
        if (invoiceLines == null) {
            invoiceLines = new ArrayList<>();
        }
        return invoiceLines;
    }

    //method to get header table total
    public double getHeaderTotal() {
        for (InvoiceLine invoiceLine : getInvoiceLines()) {
            total += invoiceLine.getLineTotal();
        }
        return total;
    }

    //method to print header table data
    public String printInvoiceHeader() {
        return "InvoiceHeader\n{\n" + "invoiceNum = " + invoiceNum
                + " , invoiceDate = " + invoiceDate + " , customerName = " + customerName + "\n}";
    }
}
