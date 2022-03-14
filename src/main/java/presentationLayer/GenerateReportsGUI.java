package presentationLayer;

import bussinessLayer.DeliveryService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerateReportsGUI extends JFrame {

    DeliveryService delivery=new DeliveryService();

    private JPanel panelTotal;
    private JPanel panelRaport1;
    private JPanel paanelNrmin;
    private JPanel panelNrMax;
    private JPanel panelRaport2;
    private JPanel panelNr;
    private JPanel panelRaport3;
    private JPanel panelComenzi;
    private JPanel panelValoare;
    private JPanel panelRaport4;
    private JPanel panelZi;

    private JButton generRaport1;
    private JLabel startHourLabel;
    private JTextField startHourText;
    private JLabel endHourLabel;
    private JTextField endHourText;

    private JButton generRaport2;
    private JLabel nrTimesLabel;
    private JTextField nrTimesText;

    private JButton generRaport3;
    private JLabel nrOrderLabel;
    private JTextField nrOrderText;
    private JLabel valueOrderLabel;
    private JTextField valueOrderText;

    private JButton generRaport4;
    private JLabel ziuaLabel;
    private JTextField ziuaText;


    public GenerateReportsGUI(DeliveryService delivery){
        this.delivery=delivery;
        this.setTitle("Generare Rapoarte");
        this.setSize(1500,200);
       delivery.getOrders();

        panelRaport1=new JPanel();
        paanelNrmin=new JPanel();
        panelNrMax=new JPanel();

        startHourLabel=new JLabel("Start Hour: ");
        startHourText=new JTextField(5);
        endHourLabel=new JLabel("End Hour: ");
        endHourText=new JTextField(5);
        generRaport1=new JButton("Raport 1");
        generRaport1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delivery.raport1(Integer.parseInt(startHourText.getText()),Integer.parseInt(endHourText.getText()));
            }
        });
        paanelNrmin.add(startHourLabel);
        paanelNrmin.add(startHourText);
        panelNrMax.add(endHourLabel);
        panelNrMax.add(endHourText);
        panelRaport1.add(paanelNrmin);
        panelRaport1.add(panelNrMax);
        panelRaport1.add(generRaport1);

        panelRaport2=new JPanel();
        panelNr=new JPanel();
      //  panelRaport2.setLayout(new GridLayout(2,0,20,20));

        nrTimesLabel=new JLabel("Nr. : ");
        nrTimesText=new JTextField(5);
        generRaport2=new JButton("Raport 2");
        generRaport2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                delivery.getOrders();
                int numar=Integer.parseInt(nrTimesText.getText());
                delivery.raport2(numar);
            }
        });
        panelNr.add(nrTimesLabel);
        panelNr.add(nrTimesText);
        panelRaport2.add(panelNr);
        panelRaport2.add(generRaport2);

        panelRaport3=new JPanel();
        panelComenzi=new JPanel();
        panelValoare=new JPanel();
    //    panelRaport3.setLayout(new GridLayout(2,0,20,20));

        nrOrderLabel=new JLabel("Nr. minim de comenzi: ");
        nrOrderText=new JTextField(5);
        valueOrderLabel=new JLabel("Valoare minima a comenzilor: ");
        valueOrderText=new JTextField(5);
        generRaport3=new JButton("Raport 3");
        generRaport3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                delivery.raport3(Integer.parseInt(nrOrderText.getText()),Integer.parseInt(valueOrderText.getText()));
            }
        });

        panelComenzi.add(nrOrderLabel);
        panelComenzi.add(nrOrderText);
        panelValoare.add(valueOrderLabel);
        panelValoare.add(valueOrderText);
        panelRaport3.add(panelComenzi);
        panelRaport3.add(panelValoare);
        panelRaport3.add(generRaport3);

        panelRaport4=new JPanel();
        panelZi=new JPanel();
   //     panelRaport4.setLayout(new GridLayout(2,0,20,20));

        ziuaLabel=new JLabel("Ziua: ");
        ziuaText=new JTextField(10);
        generRaport4=new JButton("Raport 4");
        generRaport4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String zii=ziuaText.getText();
                delivery.raport4(zii);
            }
        });
        panelZi.add(ziuaLabel);
        panelZi.add(ziuaText);
        panelRaport4.add(panelZi);
        panelRaport4.add(generRaport4);


        panelTotal=new JPanel(new GridLayout(1,2,10,10));
        panelTotal.add(panelRaport1);
        panelTotal.add(panelRaport2);
        panelTotal.add(panelRaport3);
        panelTotal.add(panelRaport4);
        BorderLayout b=new BorderLayout();
        b.setHgap(20);
        b.setVgap(20);
        this.setLayout(b);
        this.add(panelTotal,BorderLayout.CENTER);

    }

}
