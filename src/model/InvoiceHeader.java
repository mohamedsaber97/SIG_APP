package model;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class InvoiceHeader {

    //definition of invoice header data
    private int invoiceNum;
    private DateTimeFormatter invoiceDate;
    private String customerName;
    private ArrayList<InvoiceLine> invoiceLines;
}
