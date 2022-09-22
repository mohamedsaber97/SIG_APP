package model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class HeaderTableModel extends AbstractTableModel {
    String[] cols = {"No.", "Date", "Customer", "Total"};
    ArrayList<InvoiceHeader> headerArrayList;

    public HeaderTableModel(ArrayList<InvoiceHeader> headerArrayList) {
        this.headerArrayList = headerArrayList;
    }

    @Override
    public int getRowCount() {
        return headerArrayList.size();
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }

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

    public void fireHeaderData(ArrayList<InvoiceHeader> headers) {
        this.headerArrayList = headers;
        this.fireTableDataChanged();
    }
}
