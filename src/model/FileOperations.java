package model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FileOperations extends Component {
    JFileChooser readFileChooser, writeFileChooser;
    int readResult, writeResult;
    String readPath, writePath;

    //method to read invoice header data from .csv file
    public ArrayList<InvoiceHeader> readFile() {
        readFileChooser = new JFileChooser();
        readResult = readFileChooser.showOpenDialog(this);
        if (readResult == JFileChooser.APPROVE_OPTION) {
            readPath = readFileChooser.getSelectedFile().getPath();
            System.out.println("we read data from csv file");
            System.out.println("read file path is : " + readPath);
        }
        return null;
    }

    //method to write invoice header data to .csv file
    public ArrayList<InvoiceHeader> writeFile() {
        writeFileChooser = new JFileChooser();
        writeResult = writeFileChooser.showOpenDialog(this);
        if (writeResult == JFileChooser.APPROVE_OPTION) {
            writePath = writeFileChooser.getSelectedFile().getPath();
            System.out.println("we save data to csv file");
            System.out.println("write file path is : " + writePath);
        }
        return null;
    }
}
