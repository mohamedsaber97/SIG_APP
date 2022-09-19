package view;

import controller.FileMenuListener;
import controller.InvoiceHeaderListener;
import controller.InvoiceLineListener;

import javax.swing.*;
import java.awt.*;

public class InvoiceFrame extends JFrame {
    public InvoiceFrame() {

        //define frame data
        super("Invoice Frame");
        setBounds(50, 50, 1020, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2));

        //create objects from ActionListener classes
        FileMenuListener menuListener = new FileMenuListener();
        InvoiceHeaderListener headerListener = new InvoiceHeaderListener();
        InvoiceLineListener lineListener = new InvoiceLineListener();

    }

}
