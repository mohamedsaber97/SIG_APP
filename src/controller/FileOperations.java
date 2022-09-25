package controller;

import model.HeaderTableModel;
import model.InvoiceHeader;
import model.InvoiceLine;
import model.LineTableModel;
import view.HeaderDialog;
import view.InvoiceFrame;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
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
    InvoiceHeader invoiceHeader;
    ArrayList<InvoiceHeader> headerArrayList = new ArrayList<>();
    HeaderTableModel headerTableModel;
    InvoiceLine invoiceLine;
    LineTableModel lineTableModel;
    InvoiceFrame invoiceFrame;
    HeaderDialog invoiceDialog;

    //method to implement actions on file menu
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "loadFile" -> readFiles();
            case "saveFile" -> writeFiles();
            case "exit" -> System.exit(0);
            case "createHeader" -> createHeaderInvoice();
            case "deleteHeader" -> deleteSelectedInvoice();
            case "submitHeaderDialog" -> submitHeaderDialog();
            case "cancelHeaderDialog" -> cancelHeaderDialog();
            case "saveLine" -> System.out.println("we save data to csv file");
            case "cancelLine" -> System.out.println("we cancel data from csv file");
        }
    }

    //method to show InvoiceLine data after select header index
    @Override
    public void valueChanged(ListSelectionEvent e) {
        int selectedIndex = invoiceFrame.getHeaderTable().getSelectedRow();
        if (selectedIndex != -1) {
            System.out.println("-----you select row number : " + selectedIndex + "-----");
            InvoiceHeader selectedInvoice = invoiceFrame.getHeaderArrayList().get(selectedIndex);
            invoiceFrame.getNumberValueLbl().setText(String.valueOf(selectedInvoice.getInvoiceNum()));
            invoiceFrame.getDateTxt().setText(selectedInvoice.getInvoiceDate());
            invoiceFrame.getCustomerNameTxt().setText(selectedInvoice.getCustomerName());
            invoiceFrame.getTotalValueLbl().setText(String.valueOf(selectedInvoice.getHeaderTotal()));
            lineTableModel = new LineTableModel(selectedInvoice.getInvoiceLines());
            invoiceFrame.getLineTable().setModel(lineTableModel);
            lineTableModel.fireTableDataChanged();
        }
    }

    //create constructor to get receive InvoiceFrame object and send it to FileOperation class
    public FileOperations(InvoiceFrame invoiceFrame) {
        this.invoiceFrame = invoiceFrame;
    }

    //method to read invoiceHeader and invoiceLine data from .csv file
    public void readFiles() {
        readFileChooser = new JFileChooser();
        try {
            readResult = readFileChooser.showOpenDialog(invoiceFrame);
            if (readResult == JFileChooser.APPROVE_OPTION) {
                File headerFile = readFileChooser.getSelectedFile();  //save file path
                Path path = Paths.get(headerFile.getAbsolutePath());
                System.out.println("-----headerFile path is : " + path + "-----");
                List<String> headerLines = Files.readAllLines(path, Charset.defaultCharset());
                for (String line : headerLines) {
                    try {
                        String[] dataHeader = line.split(","); // use comma as separator
                        invoiceHeader = new InvoiceHeader(Integer.parseInt(dataHeader[0]), dataHeader[1], dataHeader[2]); //update data to invoiceHeader model
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
                    File lineFile = readFileChooser.getSelectedFile();  //save file path
                    Path path2 = Paths.get(lineFile.getAbsolutePath());
                    System.out.println("lineFile path is : " + path);
                    List<String> dataLines = Files.readAllLines(path2, Charset.defaultCharset());
                    for (String line : dataLines) {
                        try {
                            String[] dataLine = line.split(","); // use comma as separator
                            int headerNum = Integer.parseInt(dataLine[0]);
                            String itemName = dataLine[1];
                            double itemPrice = Double.parseDouble(dataLine[2]);
                            int count = Integer.parseInt(dataLine[3]);
                            InvoiceHeader invHeader = null;
                            for (InvoiceHeader header : headerArrayList) {
                                if (header.getInvoiceNum() == headerNum) {
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
            }

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(invoiceFrame, "can’t read file", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    //method to write invoiceHeader and invoiceLine data to .csv file
    public void writeFiles() {
        writeFileChooser = new JFileChooser();
        ArrayList<InvoiceHeader> headersList = invoiceFrame.getHeaderArrayList();
        StringBuilder headers = new StringBuilder();
        StringBuilder lines = new StringBuilder();
        for (InvoiceHeader invoiceHeader : headersList) {
            String headerCsv = invoiceHeader.getHeaderCsv();
            headers.append(headerCsv);
            headers.append("\n");
            for (InvoiceLine invoiceLine : invoiceHeader.getInvoiceLines()) {
                String lineCsv = invoiceLine.getLineCsv();
                lines.append(lineCsv);
                lines.append("\n");
            }
        }
        System.out.println("-----we get data and save it to InvoiceHeader and InvoiceLine-----");
        try {
            saveResult = writeFileChooser.showSaveDialog(invoiceFrame);
            if (saveResult == JFileChooser.APPROVE_OPTION) {
                File headerFile = writeFileChooser.getSelectedFile();
                FileWriter headerWriter = new FileWriter(headerFile);
                headerWriter.write(headers.toString());
                headerWriter.flush();
                headerWriter.close();
                System.out.println("-----data saved to InvoiceHeader.csv-----");

                saveResult = writeFileChooser.showSaveDialog(invoiceFrame);
                if (saveResult == JFileChooser.APPROVE_OPTION) {
                    File lineFile = writeFileChooser.getSelectedFile();
                    FileWriter lineWriter = new FileWriter(lineFile);
                    lineWriter.write(lines.toString());
                    lineWriter.flush();
                    lineWriter.close();
                    System.out.println("-----data saved to InvoiceLine.csv-----");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(invoiceFrame, "can’t save file", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    private void createHeaderInvoice() {
        invoiceDialog = new HeaderDialog(invoiceFrame);
        invoiceDialog.setVisible(true);
    }

    //method to delete invoiceHeader from HeaderTable
    private void deleteSelectedInvoice() {
        int selectedRow = invoiceFrame.getHeaderTable().getSelectedRow();
        if (selectedRow != -1) {
            invoiceFrame.getHeaderArrayList().remove(selectedRow);
            invoiceFrame.getHeaderTableModel().fireTableDataChanged();

            //clear data after delete invoice
            invoiceFrame.getNumberValueLbl().setText(null);
            invoiceFrame.getDateTxt().setText(null);
            invoiceFrame.getCustomerNameTxt().setText(null);
            invoiceFrame.getTotalValueLbl().setText(null);
            System.out.println("-----invoice number : " + selectedRow + "is deleted successfully-----");
        } else {
            JOptionPane.showMessageDialog(invoiceFrame, "you must select one header invoice at least", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    //method to cancel creation of HeaderInvoice
    private void cancelHeaderDialog() {
        invoiceDialog.setVisible(false);
        invoiceDialog.dispose();
        invoiceDialog = null;
    }

    //method to submit creation of HeaderInvoice
    private void submitHeaderDialog() {
        String date = invoiceDialog.getDateTxt().getText();
        String customer = invoiceDialog.getCustomerNameTxt().getText();
        int num = invoiceFrame.getNextInvoiceNum();
        try {
            String[] dateParts = date.split("-");  // "22-05-2013" -> {"22", "05", "2013"}  xy-qw-20ij
            int day = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            if (date.isEmpty() || customer.isEmpty()) {
                JOptionPane.showMessageDialog(invoiceFrame, "you must enter all data", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (dateParts.length < 3 || day > 31 || month > 12) {
                JOptionPane.showMessageDialog(invoiceFrame, "Wrong date format", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                InvoiceHeader invoiceHeader = new InvoiceHeader(num, date, customer);
                invoiceFrame.getHeaderArrayList().add(invoiceHeader);
                invoiceFrame.getHeaderTableModel().fireTableDataChanged();
                invoiceDialog.setVisible(false);
                invoiceDialog.dispose();
                invoiceDialog = null;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(invoiceFrame, "Wrong date format", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

}
