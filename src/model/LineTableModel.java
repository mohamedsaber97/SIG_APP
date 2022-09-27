package model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class LineTableModel extends AbstractTableModel {

    //define rows and cols of line table
    String[] cols = {"No.", "Item Name", "Item Price", "Count", "Total"};
    ArrayList<InvoiceLine> lineArrayList;

    public LineTableModel(ArrayList<InvoiceLine> lineArrayList) {
        this.lineArrayList = lineArrayList;
    }

    public ArrayList<InvoiceLine> getLineArrayList() {
        return lineArrayList;
    }

    //override method to get size of table rows
    @Override
    public int getRowCount() {
        return lineArrayList.size();
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

    //override method to get data of line table
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceLine invoiceLine = lineArrayList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return invoiceLine.getInvoiceHeader().getInvoiceNum();
            case 1:
                return invoiceLine.getItemName();
            case 2:
                return invoiceLine.getItemPrice();
            case 3:
                return invoiceLine.getCount();
            case 4:
                return invoiceLine.getLineTotal();
            default:
                return "";
        }
    }

}
