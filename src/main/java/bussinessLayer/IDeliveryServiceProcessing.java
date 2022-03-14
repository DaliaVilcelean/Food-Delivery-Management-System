package bussinessLayer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface IDeliveryServiceProcessing {

    //admin
    void addBaseProduct(String nume,float rating,float calorii,
                               float proteine,float grasimi, float sodiu,float pret);
    void editMenuItem(MenuItem m,String nume,String rating,String calorii,
                      String proteine,String grasimi, String sodiu,String pret);
    void deleteMenuItem(String nume);
    void raport1(int start, int fin) ;
    void raport2(int nr);
    void raport3(int nr,int minVal);
    void raport4(String zi);

    //client
    void addOrder(Order order,List<MenuItem> items);
    float computePriceOrder(Order order, List<MenuItem> ingrediente);
    List<MenuItem> searchByName(String str);
    List<MenuItem> searchByRating(float rating);
    List<MenuItem> searchByCalorii(float calorii);
    List<MenuItem> searchByProteine(float proteine);
    List<MenuItem> searchByGrasimi(float fat);
    List<MenuItem> searchBySodiu(float sodiu);
    List<MenuItem> searchByPret(float pret);

     List<MenuItem> getProduse();
     HashMap<Order, List<MenuItem>> getOrders();
}
