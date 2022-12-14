package view;

import javax.swing.*;
import java.awt.*;

public class HeaderDialog extends JDialog {

    //definition for all dialog data
    JLabel customerNameLbl, dateLbl;
    JTextField dateTxt, customerNameTxt;
    JButton submitBtn, cancelBtn;

    //define properties for all data
    public JTextField getCustomerNameTxt() {
        return customerNameTxt;
    }

    public JTextField getDateTxt() {
        return dateTxt;
    }

    //constructor for invoice creation dialog creation
    public HeaderDialog(InvoiceFrame invoiceFrame) {
        setLocation(300, 300);
        setLayout(new GridLayout(3, 2));
        setTitle("Create Header Invoice");

        customerNameLbl = new JLabel("Customer Name : ");
        customerNameTxt = new JTextField(20);

        dateLbl = new JLabel("Invoice Date : ");
        dateTxt = new JTextField(20);

        submitBtn = new JButton("submit");
        submitBtn.setActionCommand("submitHeaderDialog");
        submitBtn.addActionListener(invoiceFrame.getFileOperations());

        cancelBtn = new JButton("cancel");
        cancelBtn.setActionCommand("cancelHeaderDialog");
        cancelBtn.addActionListener(invoiceFrame.getFileOperations());

        add(dateLbl);
        add(dateTxt);
        add(customerNameLbl);
        add(customerNameTxt);
        add(submitBtn);
        add(cancelBtn);

        pack();
    }
}
