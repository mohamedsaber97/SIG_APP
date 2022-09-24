package model;

import view.InvoiceFrame;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileOperations implements ActionListener, ListSelectionListener {

    //define data to access invoice header and line files
    JFileChooser readFileChooser, writeFileChooser;
    int readResult, saveResult;
    File readFile1, readFile2, writeFile;
    InvoiceHeader invoiceHeader;
    ArrayList<InvoiceHeader> headerArrayList = new ArrayList<>();
    HeaderTableModel headerTableModel;
    InvoiceLine invoiceLine;
    LineTableModel lineTableModel;
    InvoiceFrame invoiceFrame;

    //method to implement actions on file menu
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "load" -> readFiles();
            case "save" -> saveFiles();
            case "exit" -> System.exit(0);
        }
    }

    //method to show InvoiceLine data after select header index
    @Override
    public void valueChanged(ListSelectionEvent e) {
        int selectedIndex = invoiceFrame.getHeaderTable().getSelectedRow();
        if (selectedIndex != -1) {
            System.out.println("you select row number : " + selectedIndex);
            InvoiceHeader selectedInvoice = invoiceFrame.getHeaderArrayList().get(selectedIndex);
            invoiceFrame.getNumberValueLbl().setText(selectedInvoice.invoiceNum);
            invoiceFrame.getDateTxt().setText(selectedInvoice.invoiceDate);
            invoiceFrame.getCustomerNameTxt().setText(selectedInvoice.customerName);
            invoiceFrame.getTotalValueLbl().setText(String.valueOf(selectedInvoice.total));
            lineTableModel = new LineTableModel(selectedInvoice.getInvoiceLines());
            invoiceFrame.getLineTable().setModel(lineTableModel);
            lineTableModel.fireTableDataChanged();
        }
    }

    //create constructor to get receive InvoiceFrame object and send it to FileOperation class
    public FileOperations(InvoiceFrame invoiceFrame) {
        this.invoiceFrame = invoiceFrame;
    }

    //method to read invoice header data from .csv file
    public void readFiles() {
        readFileChooser = new JFileChooser();
        try {
            readResult = readFileChooser.showOpenDialog(invoiceFrame);
            if (readResult == JFileChooser.APPROVE_OPTION) {
                readFile1 = readFileChooser.getSelectedFile();  //save file path
                Path path = Paths.get(readFile1.getAbsolutePath());
                System.out.println("readFile1 path is : " + path);
                List<String> headerLines = Files.readAllLines(path, Charset.defaultCharset());
                for (String line : headerLines) {
                    try {
                        String[] dataHeader = line.split(","); // use comma as separator
                        invoiceHeader = new InvoiceHeader(dataHeader[0], dataHeader[1], dataHeader[2]); //update data to invoiceHeader model
                        headerArrayList.add(invoiceHeader);
                        String printTxt = invoiceHeader.printInvoiceHeader();
                        System.out.println(printTxt);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(invoiceFrame, "Error in line format", "Error", JOptionPane.ERROR_MESSAGE);
                        System.exit(0);
                    }
                }
                System.out.println("-----end of read first file-----");
                readResult = readFileChooser.showOpenDialog(invoiceFrame);
                if (readResult == JFileChooser.APPROVE_OPTION) {
                    readFile2 = readFileChooser.getSelectedFile();  //save file path
                    Path path2 = Paths.get(readFile2.getAbsolutePath());
                    System.out.println("readFile2 path is : " + path);
                    List<String> dataLines = Files.readAllLines(path2, Charset.defaultCharset());
                    for (String line : dataLines) {
                        try {
                            String[] dataLine = line.split(","); // use comma as separator
                            String headerNum = dataLine[0];
                            String itemName = dataLine[1];
                            double itemPrice = Double.parseDouble(dataLine[2]);
                            int count = Integer.parseInt(dataLine[3]);
                            InvoiceHeader invHeader = null;
                            for (InvoiceHeader header : headerArrayList) {
                                if (header.getInvoiceNum().equals(headerNum)) {
                                    invHeader = header;
                                    break;
                                }
                            }
                            invoiceLine = new InvoiceLine(itemName, itemPrice, count, invHeader); //update data to invoiceHeader model
                            if (invHeader != null) {
                                invHeader.getInvoiceLines().add(invoiceLine);
                            }
                            String printTxt = invoiceLine.printInvoiceLine();
                            System.out.println(printTxt);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(invoiceFrame, "Error in line format", "Error", JOptionPane.ERROR_MESSAGE);
                            System.exit(0);
                        }
                    }
                    System.out.println("-----end of read second file-----");
                }
                invoiceFrame.setHeaderArrayList(headerArrayList);
                headerTableModel = new HeaderTableModel(headerArrayList);
                invoiceFrame.setHeaderTableModel(headerTableModel);
                invoiceFrame.getHeaderTable().setModel(headerTableModel);
                invoiceFrame.getHeaderTableModel().fireTableDataChanged();
                System.out.println("-----test get data from table after updated -- " +
                        "user name is : " + headerTableModel.headerArrayList.get(0).customerName + "-----");
            }

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(invoiceFrame, "can’t read file", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    //method to write invoice header data to .csv file
    public void saveFiles() {
        writeFileChooser = new JFileChooser();
        saveResult = writeFileChooser.showOpenDialog(invoiceFrame);
        if (saveResult == JFileChooser.APPROVE_OPTION) {
            writeFile = writeFileChooser.getSelectedFile();
            System.out.println("writeFile path is : " + writeFile);
        }
    }

}
