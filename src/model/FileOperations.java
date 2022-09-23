package model;

import view.InvoiceFrame;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileOperations extends Component {

    //define data to access invoice header and line files
    JFileChooser readFileChooser, writeFileChooser;
    int readResult, writeResult;
    File readFile, writeFile;
    InvoiceHeader invoiceHeader;
    ArrayList<InvoiceHeader> headerArrayList = new ArrayList<>();
    HeaderTableModel headerTableModel;
    InvoiceFrame invoiceFrame;

    //create constructor to receive InvoiceFrame Object
    public FileOperations(InvoiceFrame invoiceFrame) {
        this.invoiceFrame = invoiceFrame;
    }

    //method to read invoice header data from .csv file
    public ArrayList<InvoiceHeader> readFile() {
        readFileChooser = new JFileChooser();
        readResult = readFileChooser.showOpenDialog(this);
        if (readResult == JFileChooser.APPROVE_OPTION) {
            readFile = readFileChooser.getSelectedFile();  //save file path
            Path path = Paths.get(readFile.getAbsolutePath());
            System.out.println("readFile path is : " + path);
            try {
                List<String> headerLines = Files.readAllLines(path, Charset.defaultCharset());
                for (String line : headerLines) {
                    String[] data = line.split(","); // use comma as separator
                    invoiceHeader = new InvoiceHeader(data[0], data[1], data[2]); //update data to invoiceHeader model
                    headerArrayList.add(invoiceHeader);
                    String printTxt = invoiceHeader.printInvoiceHeader();
                    System.out.println(printTxt);
                }
                invoiceFrame.setHeaderArrayList(headerArrayList);
                headerTableModel = new HeaderTableModel(headerArrayList);
                invoiceFrame.setHeaderTableModel(headerTableModel);
                invoiceFrame.getHeaderTable().setModel(headerTableModel);
                invoiceFrame.getHeaderTableModel().fireTableDataChanged();
                System.out.println("test  get data from table after updated -- " +
                        "user name is : " + headerTableModel.headerArrayList.get(0).customerName);
            } catch (ArrayIndexOutOfBoundsException | IOException e) {
                e.printStackTrace();
                System.out.println("wrong file format " + e.getMessage());
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
