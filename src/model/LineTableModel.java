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
        return switch (columnIndex) {
            case 0 -> invoiceLine.getInvoiceHeader().getInvoiceNum();
            case 1 -> invoiceLine.getItemName();
            case 2 -> invoiceLine.getItemPrice();
            case 3 -> invoiceLine.getCount();
            case 4 -> invoiceLine.getLineTotal();
            default -> "";
        };
    }

}
