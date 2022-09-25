package model;

public class InvoiceLine {

    //definition of invoice lines data
    String itemName;
    double itemPrice;
    int count;
    InvoiceHeader invoiceHeader;

    //constructor for invoiceLine
    public InvoiceLine(String itemName, double itemPrice, int count, InvoiceHeader invoiceHeader) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.count = count;
        this.invoiceHeader = invoiceHeader;
    }

    //properties to get updated data of invoiceLine
    public String getItemName() {
        return itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public int getCount() {
        return count;
    }

    public InvoiceHeader getInvoiceHeader() {
        return invoiceHeader;
    }

    public double getLineTotal() {
        return itemPrice * count;
    }

    //method to get line data as csv format
    public String getLineCsv() {
        return invoiceHeader.getInvoiceNum() + "," + itemName + "," + itemPrice + "," + count;
    }

    //method to print line table data
    public String printInvoiceLine() {
        return "InvoiceLine\n{\n" + "invoiceNum = " + invoiceHeader.getInvoiceNum()
                + " , itemName = " + itemName + " , itemPrice = " + itemPrice + " , count = " + count + "\n}";
    }


}
