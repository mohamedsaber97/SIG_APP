package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InvoiceHeaderListener implements ActionListener {

    //method to implement actions on invoice lines
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "add" -> System.out.println("we add data to csv file");
            case "delete" -> System.out.println("we delete data from csv file");
        }
    }
}
