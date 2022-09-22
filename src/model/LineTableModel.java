package model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class LineTableModel extends AbstractTableModel {
    String[] cols = {"No.", "Item Name", "Item Price", "Count", "Total"};
    ArrayList<InvoiceLine> lineArrayList;

    @Override
    public int getRowCount() {
        return lineArrayList.size();
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }

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

    public void fireLineData(ArrayList<InvoiceLine> lines) {
        this.lineArrayList = lines;
        this.fireTableDataChanged();
    }
}
