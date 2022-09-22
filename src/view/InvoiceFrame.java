package view;

import controller.FileMenuListener;
import controller.InvoiceHeaderListener;
import controller.InvoiceLineListener;
import model.InvoiceHeader;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class InvoiceFrame extends JFrame {

    //definition for all frame data
    JMenuBar menuBar;
    private JMenu fileMenu;
    JMenuItem loadFileItem, saveFileItem, exitItem;
    private JPanel headerPanel, linePanel, dataPanel;
    JButton addBtn, deleteBtn, saveBtn, cancelBtn;
    JLabel numberLbl, numberValueLbl, dateLbl, nameLbl, totalLbl, totalValueLbl, itemsLbl;
    JTextField dateTxt, nameTxt;
    private JTable headerTable, lineTable;

    DefaultTableModel headerTableModel;
    DefaultTableModel lineTableModel;

    //define objects from ActionListeners classes
    FileMenuListener menuListener;
    InvoiceHeaderListener headerListener;
    InvoiceLineListener lineListener;

    ArrayList<InvoiceHeader> headerArrayList;

    //constructor for invoiceFrame creation
    public InvoiceFrame() {

        //define frame data
        super("Invoice Frame");
        setBounds(50, 50, 1020, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2));

        //declaration for objects from ActionListener classes
        menuListener = new FileMenuListener();
        headerListener = new InvoiceHeaderListener();
        lineListener = new InvoiceLineListener();

        //declaration invoice menuBar
        menuBar = new JMenuBar();
        drawFileMenu();
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        //declaration for header panel
        drawHeaderPanel();
        add(headerPanel);

        //declaration for line panel
        drawLinePanel();
        add(linePanel);

    }

    //method to draw file menu
    void drawFileMenu() {
        fileMenu = new JMenu("File");

        loadFileItem = new JMenuItem("Load File", 'L');
        loadFileItem.addActionListener(menuListener);
        loadFileItem.setActionCommand("load");

        saveFileItem = new JMenuItem("Save File", 'S');
        saveFileItem.addActionListener(menuListener);
        saveFileItem.setActionCommand("save");

        exitItem = new JMenuItem("Exit", 'E');
        exitItem.addActionListener(menuListener);
        exitItem.setActionCommand("exit");

        fileMenu.add(loadFileItem);
        fileMenu.add(saveFileItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

    }

    //method to draw header invoice panel
    void drawHeaderPanel() {
        headerPanel = new JPanel();
        headerPanel.setBorder(BorderFactory.createBevelBorder(1));
        headerPanel.setLayout(new FlowLayout());

        headerPanel.add(new JLabel("Invoice Table"));

        drawHeaderTable();
        headerPanel.add(new JScrollPane(headerTable));

        addBtn = new JButton("Create New Invoice");
        addBtn.setActionCommand("add");
        addBtn.addActionListener(headerListener);
        headerPanel.add(addBtn);

        deleteBtn = new JButton("Delete Invoice");
        deleteBtn.setActionCommand("delete");
        deleteBtn.addActionListener(headerListener);
        headerPanel.add(deleteBtn);
    }

    //method to draw header invoice table
    void drawHeaderTable() {

        String[] cols = {"No.", "Date", "Customer", "Total"};
        int rows = 4;
        headerTableModel = new DefaultTableModel(rows, cols.length);
        headerTableModel.setColumnIdentifiers(cols);
        headerTable = new JTable(headerTableModel);
    }

    //method to draw line invoice panel
    void drawLinePanel() {
        linePanel = new JPanel();
        linePanel.setBorder(BorderFactory.createBevelBorder(1));
        linePanel.setLayout(null);

        numberLbl = new JLabel("Invoice Number");
        numberLbl.setBounds(20, 10, 100, 30);
        linePanel.add(numberLbl);

        numberValueLbl = new JLabel(String.valueOf(23));
        numberValueLbl.setBounds(140, 10, 100, 30);
        linePanel.add(numberValueLbl);

        dateLbl = new JLabel("Invoice Date");
        dateLbl.setBounds(20, 50, 100, 30);
        linePanel.add(dateLbl);

        dateTxt = new JTextField(15);
        dateTxt.setBounds(140, 55, 300, 25);
        linePanel.add(dateTxt);

        nameLbl = new JLabel("Customer Name");
        nameLbl.setBounds(20, 90, 100, 30);
        linePanel.add(nameLbl);

        nameTxt = new JTextField(15);
        nameTxt.setBounds(140, 95, 300, 25);
        linePanel.add(nameTxt);

        totalLbl = new JLabel("Invoice Total");
        totalLbl.setBounds(20, 130, 100, 30);
        linePanel.add(totalLbl);

        totalValueLbl = new JLabel(String.valueOf(350.50));
        totalValueLbl.setBounds(140, 130, 100, 30);
        linePanel.add(totalValueLbl);

        itemsLbl = new JLabel("Invoice Items");
        itemsLbl.setOpaque(true);
        itemsLbl.setForeground(Color.gray);
        itemsLbl.setBounds(20, 170, 100, 30);
        linePanel.add(itemsLbl);

        drawDataPanel();
        dataPanel.setBounds(25, 200, 450, 300);
        linePanel.add(dataPanel);

        saveBtn = new JButton("Save");
        saveBtn.setBounds(100, 550, 100, 30);
        saveBtn.setActionCommand("save");
        saveBtn.addActionListener(lineListener);
        linePanel.add(saveBtn);

        cancelBtn = new JButton("Cancel");
        cancelBtn.setBounds(250, 550, 100, 30);
        cancelBtn.setActionCommand("cancel");
        cancelBtn.addActionListener(lineListener);
        linePanel.add(cancelBtn);

    }

    //method to draw line invoice data panel
    void drawDataPanel() {
        dataPanel = new JPanel();
        dataPanel.setLayout(new GridLayout());
        drawLineTable();
        dataPanel.add(new JScrollPane(lineTable));
    }

    //method to draw line invoice table
    void drawLineTable() {
        String[] cols = {"No.", "Item Name", "Item Price", "Count", "Total"};
        int rows = 4;
        lineTableModel = new DefaultTableModel(rows, cols.length);
        lineTableModel.setColumnIdentifiers(cols);
        lineTable = new JTable(lineTableModel);
    }

}
