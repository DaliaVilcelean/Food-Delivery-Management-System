package presentationLayer;

import bussinessLayer.DeliveryService;
import bussinessLayer.MenuItem;
import bussinessLayer.Order;
import dataLayer.FileWr;
import dataLayer.Serializator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Map;

public class ClientGUI extends JFrame {

    DeliveryService delivery=new DeliveryService();

    private JPanel panelAddOrder;
    private JPanel panel1AddOrder;
    private JPanel panel2AddOrder;

    private JButton addOrderBtn;
    private JButton addPlusOrderBtn;
    private JLabel tableOrderLabel;
    private JTextArea tableOrderText;
    private JLabel dataOrderLabel;
    private JTextArea dataOrderText;
    private JLabel oraOrderLabel;
    private JTextArea oraOrderText;

    private JTable table;
    private JScrollPane scrollPane;
    private JPopupMenu tableMenu;
    private JMenuItem menuBill;
    private DefaultTableModel tableModel;

    private JPanel panelSearch;
    private JButton searchBtn;
    private  JComboBox combo;
    private JTextField comboText;
    private JButton plusBtn;
    int orderId ;
    List<MenuItem> listaFiltrata;

    List<String> listaOrder;
    List<MenuItem> listaFinal;

    public ClientGUI(DeliveryService deiveryServ){
        this.delivery=deiveryServ;
        this.setTitle("Client");
        listaOrder=new ArrayList<>();
        listaFinal=new ArrayList<MenuItem>();
        orderId++;

        panelSearch=new JPanel();
        panelSearch.setLayout(new GridLayout(0,4,20,20));

        combo=new JComboBox();
       combo.addItem("Nume");
        combo.addItem("Rating");
        combo.addItem("Calorii");
        combo.addItem("Proteine");
        combo.addItem("Grasimi");
        combo.addItem("Sodiu");
        combo.addItem("Pret");
        comboText=new JTextField(10);
        searchBtn=new JButton("SEARCH");
        plusBtn=new JButton("+");
        plusBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                tableModel.setRowCount(0);
           refreshTable(delivery.importProducts(),tableModel);
            }
        });
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel.setRowCount(0);

                try {
                    if (combo.getItemAt(combo.getSelectedIndex()) == "Pret") {
                        listaFiltrata = deiveryServ.searchByPret(Float.parseFloat(comboText.getText()));
                        deiveryServ.produse=  listaFiltrata;
                        refreshTable((List<MenuItem>) listaFiltrata, tableModel);
                    }
                    if (combo.getItemAt(combo.getSelectedIndex()) == "Rating") {
                        listaFiltrata = deiveryServ.searchByRating(Float.parseFloat(comboText.getText()));
                        deiveryServ.produse=  listaFiltrata;
                        refreshTable((List<MenuItem>) listaFiltrata, tableModel);
                    }
                    if (combo.getItemAt(combo.getSelectedIndex()) == "Calorii") {
                        listaFiltrata = deiveryServ.searchByCalorii(Float.parseFloat(comboText.getText()));
                        deiveryServ.produse=  listaFiltrata;
                        refreshTable((List<MenuItem>) listaFiltrata, tableModel);
                    }
                    if (combo.getItemAt(combo.getSelectedIndex()) == "Proteine") {
                        listaFiltrata = deiveryServ.searchByProteine(Float.parseFloat(comboText.getText()));
                        deiveryServ.produse=  listaFiltrata;
                        refreshTable((List<MenuItem>) listaFiltrata, tableModel);
                    }
                    if (combo.getItemAt(combo.getSelectedIndex()) == "Grasimi") {
                        listaFiltrata = deiveryServ.searchByGrasimi(Float.parseFloat(comboText.getText()));
                        deiveryServ.produse=  listaFiltrata;
                        refreshTable((List<MenuItem>) listaFiltrata, tableModel);
                    }
                    if (combo.getItemAt(combo.getSelectedIndex()) == "Sodiu") {
                        listaFiltrata = deiveryServ.searchBySodiu(Float.parseFloat(comboText.getText()));
                        deiveryServ.produse=  listaFiltrata;
                        refreshTable((List<MenuItem>) listaFiltrata, tableModel);
                    }
                    if (combo.getItemAt(combo.getSelectedIndex()) == "Nume") {
                        listaFiltrata = deiveryServ.searchByName(comboText.getText());
                        deiveryServ.produse=  listaFiltrata;
                        refreshTable((List<MenuItem>) listaFiltrata, tableModel);
                    }
                }catch (NumberFormatException ex){
                    refreshTable(delivery.getProduse(),tableModel);
                }
            }
        });
        panelSearch.add(combo);
        panelSearch.add(comboText);
        panelSearch.add(searchBtn);
        panelSearch.add(plusBtn);

       panelAddOrder=new JPanel(new GridLayout(0,1,20,20));
       panel1AddOrder=new JPanel(new GridLayout(0,2,20,20));
       panel2AddOrder=new JPanel(new GridLayout(0,1,20,20));

       tableOrderLabel=new JLabel("MASA NR.");
       tableOrderText=new JTextArea(1,4);
       dataOrderLabel=new JLabel("Data");
       dataOrderText=new JTextArea(1,8);
       oraOrderLabel=new JLabel("LA ORA");
       oraOrderText=new JTextArea(1,4);

       addOrderBtn=new JButton("ADD ORDER");
       addPlusOrderBtn=new JButton(" + ");
       addOrderBtn.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {

               for(String str:listaOrder){
                   for(MenuItem m: deiveryServ.getProduse())
                       if(m.getNume().equals(str))
                           listaFinal.add(m);
               }
               Order order=new Order(orderId,dataOrderText.getText(),Integer.parseInt(oraOrderText.getText()),
                       Integer.parseInt(tableOrderText.getText()));

               System.out.println("orderid= "+orderId);

               ClientGUI.this.delivery.addOrder(order, listaFinal);

               System.out.println("Pretul total:"+deiveryServ.computePriceOrder(order,listaFinal));

               JOptionPane.showInputDialog("Comanda Plasata");
               System.out.println(order.getData()+" "+order.getHours()+" "+order.getTable());

               try {
                   FileWr.writeBill(order,listaFinal);
               } catch (IOException exception) {
                   exception.printStackTrace();
               }


           }
       });
       addPlusOrderBtn.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               int row=table.getSelectedRow();
               String titlu=tableModel.getValueAt(row,0).toString();

               listaOrder.add(titlu);
           }
       });

       panel1AddOrder.add(tableOrderLabel);
       panel1AddOrder.add(tableOrderText);
       panel1AddOrder.add(dataOrderLabel);
       panel1AddOrder.add(dataOrderText);
       panel1AddOrder.add(oraOrderLabel);
       panel1AddOrder.add(oraOrderText);
       panel1AddOrder.add(addOrderBtn);
       panel1AddOrder.add(addPlusOrderBtn);

        panelAddOrder.add(panelSearch);
       panelAddOrder.add(panel1AddOrder);
       panelAddOrder.add(panel2AddOrder);


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

        refreshTable(delivery.getProduse(),tableModel);

        scrollPane=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
       scrollPane.setSize(1000,500);
       BorderLayout b=new BorderLayout();
        b.setHgap(20);
        b.setVgap(20);
        this.setLayout(b);
        this.add(panelAddOrder,BorderLayout.SOUTH);
        this.add(scrollPane,BorderLayout.CENTER);

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
