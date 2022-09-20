package controller;

import model.FileOperations;
import model.InvoiceHeader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FileMenuListener extends FileOperations implements ActionListener {

    //define data to make actions file menu
    FileOperations fileOperations = new FileOperations();
    ArrayList<InvoiceHeader> readData = new ArrayList<>();
    ArrayList<InvoiceHeader> savedData = new ArrayList<>();

    //method to implement actions on file menu
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "load" -> readData = fileOperations.readFile();
            case "save" -> savedData = fileOperations.writeFile();
            case "exit" -> System.exit(0);
        }
    }
}
