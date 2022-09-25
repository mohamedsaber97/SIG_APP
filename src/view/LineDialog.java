package view;

import javax.swing.*;
import java.awt.*;

public class LineDialog extends JDialog {
    //definition for all dialog data
    JLabel itemNameLbl, itemPriceLbl, itemCountLbl;
    JTextField itemNameTxt, itemPriceTxt, itemCountTxt;
    JButton submitBtn, cancelBtn;

    public JTextField getItemPriceTxt() {
        return itemPriceTxt;
    }

    public JTextField getItemNameTxt() {
        return itemNameTxt;
    }

    public JTextField getItemCountTxt() {
        return itemCountTxt;
    }

    //constructor for invoice creation dialog creation
    public LineDialog(InvoiceFrame invoiceFrame) {
        setLocation(600, 600);
        setLayout(new GridLayout(3, 2));
        setTitle("Create Line Invoice");

        itemNameLbl = new JLabel("Item Name : ");
        itemNameTxt = new JTextField(20);

        itemCountLbl = new JLabel("Item Count : ");
        itemCountTxt = new JTextField(20);

        itemPriceLbl = new JLabel("Item Price : ");
        itemPriceTxt = new JTextField(20);

        submitBtn = new JButton("save");
        submitBtn.setActionCommand("saveLine");
        submitBtn.addActionListener(invoiceFrame.getFileOperations());

        cancelBtn = new JButton("cancel");
        cancelBtn.setActionCommand("cancelLine");
        cancelBtn.addActionListener(invoiceFrame.getFileOperations());

        add(itemNameLbl);
        add(itemNameTxt);
        add(itemCountLbl);
        add(itemCountTxt);
        add(itemPriceLbl);
        add(itemPriceTxt);
        add(submitBtn);
        add(cancelBtn);

        pack();
    }
}
