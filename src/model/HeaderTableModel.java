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

    //override method to get name of table cols
    @Override
    public String getColumnName(int column) {
        return cols[column];
    }

    //override method to get data of header table
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceHeader invoiceHeader = headerArrayList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return invoiceHeader.getInvoiceNum();
            case 1:
                return invoiceHeader.getInvoiceDate();
            case 2:
                return invoiceHeader.getCustomerName();
            case 3:
                return invoiceHeader.getHeaderTotal();
            default:
                return "";
        }
    }
}
