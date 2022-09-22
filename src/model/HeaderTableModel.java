package model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class HeaderTableModel extends AbstractTableModel {

   //define rows and cols of header table
    String[] cols = {"No.", "Date", "Customer", "Total"};
    ArrayList<InvoiceHeader> headerArrayList;

    public HeaderTableModel(ArrayList<InvoiceHeader> headerArrayList) {
        this.headerArrayList = headerArrayList;
    }

    //override method to get size of table rows
    @Override
    public int getRowCount() {
        return headerArrayList.size();
    }

    //override method to get length of table cols
    @Override
    public int getColumnCount() {
        return cols.length;
    }

    //override method to get data of header table
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceHeader invoiceHeader = headerArrayList.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> invoiceHeader.getInvoiceNum();
            case 1 -> invoiceHeader.getInvoiceDate();
            case 2 -> invoiceHeader.getCustomerName();
            case 3 -> invoiceHeader.getHeaderTotal();
            default -> "";
        };
    }

    //method to update data into header table
    public void fireHeaderData(ArrayList<InvoiceHeader> headers) {
        this.headerArrayList = headers;
        this.fireTableDataChanged();
    }
}
