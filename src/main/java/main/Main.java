package main;

import bussinessLayer.*;
import dataLayer.Serializator;
import dataLayer.User;
import dataLayer.UserSerialize;
import presentationLayer.AdminGUI;
import presentationLayer.ChefGUI;
import presentationLayer.ClientGUI;
import presentationLayer.LogInClass;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args){

        Serializator serializator=new Serializator("restaurant.txt");
        DeliveryService deliveryService =new DeliveryService();
        serializator.readData(deliveryService);
        serializator.writeData( deliveryService);


        for(MenuItem m:deliveryService.getProduse()){
            System.out.println(m.getNume());
            System.out.println("\n");
        }


        System.out.println(deliveryService.getOrders());


        LogInClass login=new LogInClass(deliveryService);
        login.getListaUser();

    }

}
