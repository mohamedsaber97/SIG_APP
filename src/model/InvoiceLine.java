package model;

public class InvoiceLine {

    //definition of invoice lines data
    String invoiceNum;
    String itemName;
    double itemPrice;
    int count;

    //constructor for invoiceLine
    public InvoiceLine(String invoiceNum, String itemName, double itemPrice, int count) {
        this.invoiceNum = invoiceNum;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.count = count;
    }

    //property to get updated data of invoiceLine
    public String getInvoiceNum() {
        return invoiceNum;
    }

    public String getItemName() {
        return itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public int getCount() {
        return count;
    }
}
