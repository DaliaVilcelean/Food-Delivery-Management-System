package dataLayer;

import bussinessLayer.DeliveryService;
import bussinessLayer.IDeliveryServiceProcessing;
import bussinessLayer.MenuItem;
import bussinessLayer.Order;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Serializator {

    private static final long serialVersionUID = -3241770291249316584L;
    private static File file;
    // private static File file;
    private File file1;

    public Serializator(String fileName){
        this.file=new File(fileName);
    }

    public void writeData(IDeliveryServiceProcessing restaurant){
        FileOutputStream fileOutputStream;
        try{
            fileOutputStream=new FileOutputStream(file);
            ObjectOutputStream outputStream=new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(restaurant.getProduse());
            outputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeDataOrder(Map<Order, List<MenuItem>> order){
        FileOutputStream fileOutputStream;
        try{
            fileOutputStream=new FileOutputStream(file);
            ObjectOutputStream outputStream=new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(order);
            outputStream.close();
            System.out.println("Serializare cu succes");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readData(DeliveryService service){
        FileInputStream fileInputStream;
        try{
            fileInputStream=new FileInputStream(file);
            ObjectInputStream inputStream=new ObjectInputStream(fileInputStream);
            service.setProduse((List<MenuItem>) inputStream.readObject());

            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Map<Order, List<MenuItem>> readDataOrder(){
        FileInputStream fileInputStream;
        Map<Order, List<MenuItem>> o=null;
        try{
            fileInputStream=new FileInputStream(file);
            ObjectInputStream inputStream=new ObjectInputStream(fileInputStream);
            o= (Map<Order, List<MenuItem>>) inputStream.readObject();

            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return o;
    }

}
