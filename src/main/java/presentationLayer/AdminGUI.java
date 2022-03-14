package presentationLayer;

import bussinessLayer.BaseProduct;
import bussinessLayer.CompositeProduct;
import bussinessLayer.DeliveryService;
import bussinessLayer.MenuItem;
import dataLayer.Serializator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class AdminGUI extends JFrame {

    DeliveryService delivery=new DeliveryService();
    List<MenuItem> produse=new ArrayList<MenuItem>();

    private JPanel  panelMenuItem;
    private JPanel panelBaseProduct;
    private JPanel panelCompositeProduct;
    private JPanel panel1CompositeProduct;
    private JPanel panel2CompositeProduct;
    private JPanel panel3CompositeProduct;

    private JButton addBaseProductButton;
    private JLabel nameBaseProductLabel;
    private JTextField nameBPText;
    private JLabel ratingBaseProductLabel;
    private JTextField ratingBPText;
    private JLabel caloriiBaseProductLabel;
    private JTextField caloriiBPText;
    private JLabel protBaseProductLabel;
    private JTextField protBPText;
    private JLabel fatBaseProductLabel;
    private JTextField fatBPText;
    private JLabel sodiuBaseProductLabel;
    private JTextField sodiuBPText;
    private JLabel pretBaseProductLabel;
    private JTextField pretBPText;

    private JButton addCompositeProductBTN;
    private JButton addPlusCPBtn;
    private JLabel nameCPLabel;
    private JTextField nameCPArea;

    JTable table=new JTable();
    DefaultTableModel tableModel=new DefaultTableModel();
    private JButton deleteMenuItem;
    private JButton editMenuItem;
    private JButton generateReports;
    private JScrollPane tableScrollPane;

    private List<String> listaComp;

    GenerateReportsGUI reports=new GenerateReportsGUI(delivery);

    public AdminGUI(DeliveryService delivery){

        this.delivery=delivery;
        this.setTitle("Administrator");

        panelBaseProduct=new JPanel();
        panelBaseProduct.setLayout(new GridLayout(0,2,20,20));

        nameBaseProductLabel=new JLabel("NUME");
        nameBPText=new JTextField(10);
        ratingBaseProductLabel=new JLabel("RATING");
        ratingBPText=new JTextField(4);
        caloriiBaseProductLabel=new JLabel("CALORII");
        caloriiBPText=new JTextField(4);
        protBaseProductLabel=new JLabel("PROTEINE");
        protBPText=new JTextField(4);
        fatBaseProductLabel=new JLabel("GRASIMI");
        fatBPText=new JTextField(4);
        sodiuBaseProductLabel=new JLabel("SODIU");
        sodiuBPText=new JTextField(4);
        pretBaseProductLabel=new JLabel("PRET");
        pretBPText=new JTextField(5);
        addBaseProductButton=new JButton("ADD BASE PRODUCT");
        deleteMenuItem= new JButton("REMOVE");
        editMenuItem=new JButton("EDIT");
        generateReports=new JButton("GENERATE REPORTS");
        addBaseProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuItem produs = null;
                try {
                    float rat = Float.parseFloat(ratingBPText.getText());
                    float cal = Float.parseFloat(caloriiBPText.getText());
                    float pro = Float.parseFloat(protBPText.getText());
                    float fat = Float.parseFloat(fatBPText.getText());
                    float sod = Float.parseFloat(sodiuBPText.getText());
                    float pret = Float.parseFloat(pretBPText.getText());

                    AdminGUI.this.delivery.addBaseProduct(nameBPText.getText(), rat,
                            cal, pro, fat, sod,
                            pret);
                     produs=new BaseProduct(nameBPText.getText(), rat,
                            cal, pro, fat, sod,
                            pret);
                }catch (AssertionError a){

                }
                addRow(produs,tableModel);
               table.setModel(tableModel);

                Serializator serializator=new Serializator("restaurant.txt");
                serializator.writeData(AdminGUI.this.delivery);
            }
        });
        panelBaseProduct.add(nameBaseProductLabel);
        panelBaseProduct.add(nameBPText);
        panelBaseProduct.add(ratingBaseProductLabel);
        panelBaseProduct.add(ratingBPText);
        panelBaseProduct.add(caloriiBaseProductLabel);
        panelBaseProduct.add(caloriiBPText);
        panelBaseProduct.add(protBaseProductLabel);
        panelBaseProduct.add(protBPText);
        panelBaseProduct.add(fatBaseProductLabel);
        panelBaseProduct.add(fatBPText);
        panelBaseProduct.add(sodiuBaseProductLabel);
        panelBaseProduct.add(sodiuBPText);
        panelBaseProduct.add(pretBaseProductLabel);
        panelBaseProduct.add(pretBPText);
        panelBaseProduct.add(addBaseProductButton);
        panelBaseProduct.add(deleteMenuItem);
        panelBaseProduct.add(editMenuItem);
        panelBaseProduct.add(generateReports);
        generateReports.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reports.setVisible(true);
            }
        });

        panelCompositeProduct=new JPanel(new GridLayout(0,1,20,20));
        panel1CompositeProduct=new JPanel(new GridLayout(0,1,20,20));
        panel2CompositeProduct=new JPanel(new GridLayout(0,1,20,20));
        panel3CompositeProduct=new JPanel(new GridLayout(0,1,20,20));

        listaComp=new ArrayList<String>();

        nameCPLabel=new JLabel("NUME");
        nameCPArea=new JTextField(10);
        addCompositeProductBTN=new JButton("ADD COMP. PRODUCT");
        addPlusCPBtn=new JButton(" + ");
        addCompositeProductBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                CompositeProduct product = new CompositeProduct();
                for (String str : listaComp) {
                    product.computeAll(AdminGUI.this.delivery.findByName(str));
                }
                String titlu=nameCPArea.getText();
                product.setNume(titlu);
                System.out.println(product.getNume()+" "+product.getPret()+" "+product.getGrasimi());
                listaComp.clear();
                AdminGUI.this.delivery.addMenuItem(product);
                // tableModel.setRowCount(0);
                addRow(product,tableModel);
                Serializator serializator=new Serializator("restaurant.txt");
                serializator.writeData(delivery);
            }
        });
        addPlusCPBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row=table.getSelectedRow();
                String titlu=tableModel.getValueAt(row,0).toString();

                listaComp.add(titlu);
            }
        });
        panel1CompositeProduct.add(nameCPLabel);
        panel1CompositeProduct.add(nameCPArea);


        panel3CompositeProduct.add(addCompositeProductBTN);
        panel3CompositeProduct.add(addPlusCPBtn);

        panelCompositeProduct.add(panel1CompositeProduct);
        panelCompositeProduct.add(panel2CompositeProduct);
        panelCompositeProduct.add(panel3CompositeProduct);

        panelMenuItem=new JPanel(new GridLayout(1,2,10,10));
        panelMenuItem.add(panelBaseProduct);
        panelMenuItem.add(panelCompositeProduct);

        table=new JTable();

        tableModel=new DefaultTableModel();
        tableModel.setColumnCount(8);
        String[] cols={"NAME","RATING","CALORII","PROTEINE","GRASIMI","SODIU","PRET"};
        tableModel.setColumnIdentifiers(cols);
        table.setModel(tableModel);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
               Point point=e.getPoint();
               int curr=table.rowAtPoint(point);
               table.setRowSelectionInterval(curr,curr);
            }
        });

        deleteMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MenuItem menuItem=new BaseProduct();
                int row=table.getSelectedRow();
                    String nume = tableModel.getValueAt(row, 0).toString();
                    delivery.deleteMenuItem(nume);
                    tableModel.removeRow(row);

                Serializator serializator=new Serializator("restaurant.txt");
                serializator.writeData(AdminGUI.this.delivery);
            }
        });

        editMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selected = table.getSelectedRow();
                String nume = table.getValueAt(selected, 0).toString();
                String rating=table.getValueAt(selected,1).toString();
                String calorii=table.getValueAt(selected,2).toString();
                String proteine=table.getValueAt(selected,3).toString();
                String grasimi=table.getValueAt(selected,4).toString();
                String sodiu=table.getValueAt(selected,5).toString();
                String pret=table.getValueAt(selected,6).toString();
                List<String> sell = new ArrayList<>();
                MenuItem m = delivery.findByName(nume);

                delivery.editMenuItem(m, nume,rating,calorii,proteine,grasimi,sodiu,pret);

                Serializator serializator = new Serializator("restaurant.txt");
                serializator.writeData(AdminGUI.this.delivery);
            }
        });

        refreshTable(delivery.getProduse(),tableModel);
        Serializator serializator=new Serializator("restaurant.txt");
        serializator.writeData(AdminGUI.this.delivery);

        tableScrollPane=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tableScrollPane.setSize(1000,500);
        BorderLayout b=new BorderLayout();
        b.setHgap(20);
        b.setVgap(20);
        this.setLayout(b);
        this.add(panelMenuItem,BorderLayout.SOUTH);
        this.add(tableScrollPane,BorderLayout.CENTER);
    }

    public void addRow(MenuItem menu,DefaultTableModel tableModel){
        Object[] obj={menu.getNume(),menu.getRating(),menu.getCalorii(),menu.getProteine(),
                menu.getGrasimi(),menu.getSodiu(),menu.getPret(),"-"};
        tableModel.addRow(obj);
    }

    public  void refreshTable(List<MenuItem> linie, DefaultTableModel tableModel){

        if(linie.size()==0)
            return;
        for(MenuItem menu:linie){
            Object[] obj={menu.getNume(),menu.getRating(),menu.getCalorii(),menu.getProteine(),
                    menu.getGrasimi(),menu.getSodiu(),menu.getPret(),"-"};
            tableModel.addRow(obj);
        }
        this.repaint();
        this.revalidate();
    }
}
