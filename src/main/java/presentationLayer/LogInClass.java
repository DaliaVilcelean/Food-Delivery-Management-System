package presentationLayer;

import bussinessLayer.DeliveryService;
import dataLayer.User;
import dataLayer.UserSerialize;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LogInClass extends JFrame implements Serializable {
    private List<User> listaUser;
    DeliveryService delivery;

    private JButton loginBtn;
    private JButton newUser;
    private JPanel loginpanel;
    private JTextField txuser;
    private JTextField pass;
    private JLabel username;
    private JLabel password;



public LogInClass(DeliveryService delivery){
    this.delivery=delivery;
    this.setTitle("Log In");

    loginBtn=new JButton("Log In");
    loginpanel=new JPanel();
    txuser=new JTextField(15);
    pass=new JPasswordField(15);
    newUser=new JButton("Register");
    username=new JLabel("Username: ");
    password=new JLabel("Password: ");

    setSize(300,200);
    setLocation(500,280);
    loginpanel.setLayout(null);

    txuser.setBounds(70,30,150,20);
    pass.setBounds(70,65,150,20);
    loginBtn.setBounds(110,100,80,20);
    newUser.setBounds(110,135,80,20);
    username.setBounds(20,28,80,20);
    password.setBounds(20,63,80,20);

    loginpanel.add(loginBtn);
    loginpanel.add(txuser);
    loginpanel.add(pass);
    loginpanel.add(newUser);
    loginpanel.add(username);
    loginpanel.add(password);

    getContentPane().add(loginpanel);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

    listaUser=new ArrayList<User>();
    User user=new User("Pop Ion","popion123",false);
    listaUser.add(user);
    User user1=new User("Talce Andreea","123456",false);
    listaUser.add(user1);
    User user3=new User("Rad Maria","pass3",true);
    listaUser.add(user3);

   // System.out.println(listaUser);

    newUser.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            NewUserGUI newUserGUI=new NewUserGUI();
            dispose();
        }
    });

    loginBtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e){
            getListaUser();
            for(User user:listaUser){
                System.out.println(user.getUsername()+" "+user.getPassword()+" "+user.isClient());
            }

            for(User user2:listaUser){
                if(user2.getUsername().equals(txuser.getText()) && user2.getPassword().equals(pass.getText()) &&
                user2.isClient()==false){
                    AdminGUI adminGUI=new AdminGUI(delivery);
                    adminGUI.setVisible(true);
                    dispose();
                }
                if(user2.getUsername().equals(txuser.getText()) && user2.getPassword().equals(pass.getText()) &&
                        user2.isClient()==true) {
                    ClientGUI clientGUI = new ClientGUI(delivery);
                    clientGUI.setVisible(true);
                    ChefGUI chefGUI=new ChefGUI(delivery);
                    delivery.addObserver(chefGUI);
                    dispose();
                }
            }
            dispose();
        }
    });
}

    public List<User> getListaUser() {
    UserSerialize ser=new UserSerialize("user.txt");
    listaUser=ser.readDataUser();
    return listaUser;
    }

    public void setListaUser(List<User> listaUser) {
        this.listaUser = listaUser;
    }

    public void addUser(String username, String password){

        UserSerialize userSerialize=new UserSerialize("user.txt");
        listaUser=userSerialize.readDataUser();

    User user=new User(username,password,true);
    int initSize=listaUser.size();
    listaUser.add(user);
        System.out.println(listaUser);
        userSerialize.writeDataUser(listaUser);
    assert initSize+1==listaUser.size();
    assert user!=null;
    }
}
