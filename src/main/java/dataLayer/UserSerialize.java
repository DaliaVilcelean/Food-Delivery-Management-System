package dataLayer;

import presentationLayer.LogInClass;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserSerialize {

    private static File file;
    // private static File file;
    private File file1;

    public UserSerialize(String fileName){
        this.file=new File(fileName);
    }

    public static void writeDataUser(List<User> users){
        FileOutputStream fileOutputStream;
        try{
            fileOutputStream=new FileOutputStream(file);
            ObjectOutputStream outputStream=new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(users);
            outputStream.close();
            System.out.println("Serializare cu succes");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<User> readDataUser(){
        FileInputStream fileInputStream;
       List<User> user=null;
        try{
            fileInputStream=new FileInputStream(file);
            ObjectInputStream inputStream=new ObjectInputStream(fileInputStream);
          user= (List<User>) inputStream.readObject();

            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return user;

    }

}
