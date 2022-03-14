package presentationLayer;

import bussinessLayer.DeliveryService;
import dataLayer.User;
import dataLayer.UserSerialize;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class NewUserGUI extends JFrame{
    JButton create;
    JPanel newUserPanel;
    JTextField txuserer;
    JTextField passer;
    DeliveryService deliveryService;
    LogInClass logInClass;
    List<User> lista;


    public NewUserGUI() {
       deliveryService=new DeliveryService();
       logInClass=new LogInClass(deliveryService);
        this.setTitle("Registration");
     logInClass.getListaUser();

        create = new JButton("Create");
        newUserPanel = new JPanel();
        txuserer = new JTextField(15);
        passer = new JPasswordField(15);


        setSize(300, 200);
        setLocation(500, 280);
        newUserPanel.setLayout(null);


        txuserer.setBounds(70, 30, 150, 20);
        passer.setBounds(70, 65, 150, 20);
        create.setBounds(110, 100, 80, 20);

        newUserPanel.add(create);
        newUserPanel.add(txuserer);
        newUserPanel.add(passer);

        getContentPane().add(newUserPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

     logInClass.getListaUser();

        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user=null;

                String username=txuserer.getText();
                String password=passer.getText();

                logInClass.addUser(username,password);
                user=new User(username,password,true);

                LogInClass logInClass=new LogInClass(deliveryService);
                dispose();
            }
        });

    }


}
