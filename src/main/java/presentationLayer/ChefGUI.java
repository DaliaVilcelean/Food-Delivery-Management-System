package presentationLayer;

import bussinessLayer.DeliveryService;
import bussinessLayer.MenuItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;

public class ChefGUI extends JFrame implements Observer {

    private JTextArea lista=new JTextArea();

    @Override
    public void update(Observable o, Object arg) {

        JOptionPane.showMessageDialog(null,"Ati Primit o Noua Comanda","COMANDA!",
                JOptionPane.INFORMATION_MESSAGE);

        lista.append("Ati Primit o Comanda");


    }


    public ChefGUI(DeliveryService delivery){

        this.setTitle("Chef");
        lista.setPreferredSize(new Dimension(300,300));
        this.setSize(600,600);
        this.setVisible(true);
        JPanel panel=new JPanel();
        panel.add(lista);
        this.add(panel);
        this.setLocationRelativeTo(null);
        this.setLocation(this.getX()+400,this.getY());

    }


    public void update(){
        this.getContentPane().removeAll();
    }

}
