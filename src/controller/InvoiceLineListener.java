package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InvoiceLineListener implements ActionListener {

    //method to implement actions on invoice headers
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "save" -> System.out.println("we save data to csv file");
            case "cancel" -> System.out.println("we cancel data from csv file");
        }
    }
}
