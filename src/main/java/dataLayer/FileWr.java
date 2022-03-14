package dataLayer;

import bussinessLayer.DeliveryService;
import bussinessLayer.MenuItem;
import bussinessLayer.Order;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileWr {


    public static void writeBill(Order order, List<MenuItem> ingrediente) throws IOException {
        File file1=new File("Chitanta"+order.getOrderId()+".txt");
        order.setOrderId(order.getOrderId()+1);
       DeliveryService ds=new DeliveryService();


         file1.createNewFile();
            FileWriter writer=new FileWriter(file1);

            writer.write("Comanda de la masa "+order.getTable()+":\n");
            System.out.println("Comanda de la masa "+order.getTable()+":\n");
            writer.write("Produsele comandate:\n");
            System.out.println("Produsele comandate:\n");
            for(MenuItem menu:ingrediente){
                writer.write(menu.toString());
                System.out.println(menu.toString());
            }
            writer.write("\n");
            writer.write("Pretul Total: "+ds.computePriceOrder(order,ingrediente));
            System.out.println("Pretul Total: "+ds.computePriceOrder(order,ingrediente));
        System.out.println(order.getTotal());

            writer.close();

    }

    public static void writeRaport3(List<String> name, int nr, int minVal){

        File file=new File("Raport3.txt");
        try {
            file.createNewFile();
            FileWriter writer=new FileWriter(file);

            writer.write("Clientii care au comandat de mai mult de "+nr+" ori si comanda e mai scumpa de "+minVal+" ori\n");
            writer.write("\n");

            writer.write(name.toString());

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void writeRaport4(String zi, Map<MenuItem, Long> listaFr) {

        File file=new File("Raport4.txt");
        try {
            file.createNewFile();
            FileWriter writer=new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
