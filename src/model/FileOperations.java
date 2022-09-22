package model;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileOperations extends Component {

    //define data to access invoice header and line files
    JFileChooser readFileChooser, writeFileChooser;
    int readResult, writeResult;
    File readFile, writeFile;
    FileReader fileReader;
    BufferedReader bufferedReader;
    InvoiceHeader invoiceHeader;
    ArrayList<InvoiceHeader> headerArrayList = new ArrayList<>();

    //method to read invoice header data from .csv file
    public ArrayList<InvoiceHeader> readFile() {
        readFileChooser = new JFileChooser();
        readResult = readFileChooser.showOpenDialog(this);
        if (readResult == JFileChooser.APPROVE_OPTION) {
            readFile = readFileChooser.getSelectedFile();  //save file path
            System.out.println("readFile path is : " + readFile);
            String line;
            try {
                fileReader = new FileReader(readFile); //to read saved file
                bufferedReader = new BufferedReader(fileReader); //get file data and save it into buffer
                while ((line = bufferedReader.readLine()) != null) {
                    String[] data = line.split(","); // use comma as separator
                    invoiceHeader = new InvoiceHeader(data[0], data[1], data[2]); //update data to invoiceHeader model
                    headerArrayList.add(invoiceHeader);
                    System.out.println("Invoice "
                            + "["
                            + invoiceHeader.getInvoiceNum()
                            + " , " + invoiceHeader.getInvoiceDate()
                            + " , " + invoiceHeader.getCustomerName()
                            + "]");
                }
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return headerArrayList;
    }

    //method to write invoice header data to .csv file
    public ArrayList<InvoiceHeader> writeFile() {
        writeFileChooser = new JFileChooser();
        writeResult = writeFileChooser.showOpenDialog(this);
        if (writeResult == JFileChooser.APPROVE_OPTION) {
            writeFile = writeFileChooser.getSelectedFile();
            System.out.println("writeFile path is : " + writeFile);
        }
        return null;
    }
}
