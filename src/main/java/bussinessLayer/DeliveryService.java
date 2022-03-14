package bussinessLayer;

import dataLayer.Serializator;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeliveryService extends Observable implements IDeliveryServiceProcessing, Serializable {
    private static final long serialVersionUID = -3241770291249316584L;

    public List<MenuItem> produse;
    private Map<Order,List<MenuItem>> orders=new HashMap<Order, List<MenuItem>>();
    private int nrOrders;

    public DeliveryService(){

        produse=new ArrayList<MenuItem>();

    }

    public void addMenuItem(MenuItem menuItem){
        produse.add(menuItem);
    }
    public List<MenuItem> getProduse() {
        return produse;
    }

    public void setOrders(Map<Order, List<MenuItem>> orders) {
        this.orders = orders;
    }

    public HashMap<Order, List<MenuItem>> getOrders() {
        Serializator serializator=new Serializator("order.txt");
        orders=serializator.readDataOrder();
        return (HashMap<Order, List<MenuItem>>) orders;
    }

    public void setProduse(List<MenuItem> produse) {

        this.produse = produse;
    }

    public void addBaseProduct(String nume,float rating,float calorii,
                               float proteine,float grasimi, float sodiu,float pret){
        assert nume!=null;
        assert pret!=0;

        MenuItem menuItem=new BaseProduct(nume,rating,calorii,proteine,grasimi,sodiu,pret);
        int initSize=produse.size();
        addMenuItem(menuItem);
        assert initSize+1==produse.size();
        assert menuItem!=null;
    }

    public MenuItem findByName(String nume){
        for(MenuItem m:produse){
            if(m.getNume().equals(nume))
                return m;
        }
        return null;
    }

    public void editMenuItem(MenuItem m,String nume,String rating,String calorii,
                             String proteine,String grasimi, String sodiu,String pret){

        assert m!=null;
        assert nume!=null ;

                m.setNume(nume);
                m.setCalorii(Float.parseFloat(calorii));
                m.setGrasimi(Float.parseFloat(grasimi));
                m.setPret(Float.parseFloat(pret));
                m.setProteine(Float.parseFloat(proteine));
                m.setRating(Float.parseFloat(rating));
                m.setSodiu(Float.parseFloat(sodiu));

                assert m!=null;
            }


    public void deleteMenuItem(String nume){
        assert nume!=null;
        MenuItem m1=null;

        for(MenuItem m:produse) {
                if (m.getNume().equals(nume))
                    m1=m;
            }
        produse.remove(m1);

        }

    public void addOrder(Order order,List<MenuItem> items){

       assert order!=null;
        nrOrders++;

        Serializator serializator=new Serializator("order.txt");
       orders=serializator.readDataOrder();
        orders.put(order,items);
        System.out.println(items);
        System.out.println(orders);
        serializator.writeDataOrder(orders);

        setChanged();
        notifyObservers(items);
        for(MenuItem m:items)
            System.out.println(m.getNume()+" "+m.getPret()+"\n");
    }

    public float computePriceOrder(Order order, List<MenuItem> ingrediente){
        assert order!=null:"Nicio comanda";
        float pret=0;

        for (MenuItem menuItem : ingrediente) {
            pret+= menuItem.getPret();
        }

        order.setTotal(pret);
        return pret;
    }

    public List<MenuItem> importProducts() {
        String file = "products.csv";
        Stream<String> readFile = null;
        produse = new ArrayList<MenuItem>();
        try {
            readFile = Files.lines(Paths.get(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

            List<String> stringList = new ArrayList<>();
            readFile.forEach(line -> {
                stringList.add(line);
            });
            List<String> stringNr = stringList.stream().distinct().collect(Collectors.toList());
            for (String strr : stringNr) {
                String[] arr = strr.split(",", 7);

                if (arr[0].compareTo("Title") != 0) {

                    MenuItem baseProduct = new BaseProduct(arr[0], Float.parseFloat(arr[1]),
                            Integer.parseInt(arr[2]), Integer.parseInt(arr[3]), Integer.parseInt(arr[4]),
                            Integer.parseInt(arr[5]), Float.parseFloat(arr[6]));
                    produse.add(baseProduct);
                }
            }

            Set<String> set = new HashSet<>(produse.size());
            produse.removeIf(p -> !set.add(p.getNume()));
            //  Serializator.readData(this);
            return produse;

        }


    public List<MenuItem> searchByName(String str){
   //     String str;
        List<MenuItem> list=  produse.stream()
                .filter(name->(name.getNume().toLowerCase().contains(str.toLowerCase()))).collect(Collectors.toList());
        return  list;
    }
    public List<MenuItem> searchByRating(float rating){
        List<MenuItem> list=  produse.stream()
                .filter(name->(name.getRating()==rating)).collect(Collectors.toList());
        return list;
    }
    public List<MenuItem> searchByCalorii(float calorii){
        List<MenuItem> list=  produse.stream()
                .filter(name->(name.getCalorii()==calorii)).collect(Collectors.toList());
        return list;
    }
    public List<MenuItem> searchByProteine(float proteine){
        List<MenuItem> list=  produse.stream()
                .filter(name->(name.getProteine()==proteine)).collect(Collectors.toList());
        return list;
    }
    public List<MenuItem> searchByGrasimi(float fat){
        List<MenuItem> list= produse.stream()
                .filter(name->(name.getGrasimi()==fat)).collect(Collectors.toList());
        return list;
    }
    public List<MenuItem> searchBySodiu(float sodiu){
        List<MenuItem> list=  produse.stream()
                .filter(name->(name.getSodiu()==sodiu)).collect(Collectors.toList());
        return list;
    }
    public List<MenuItem> searchByPret(float pret){
        List<MenuItem> list=  produse.stream()
                .filter(name->(name.getPret()==pret)).collect(Collectors.toList());
        return list;
    }

    public  void raport1(int start, int fin) {
        assert start >= 0;
        assert fin <= 23;
     Set<Order> keySet=orders.keySet();
     Stream<Order> stream=keySet.stream();
     Set<Order> wrr=stream.map(order -> order)
             .filter(hour->(hour.getHours()>=start && hour.getHours()<=fin)).collect(Collectors.toSet());
        File file = new File("Raport1.txt");
        try {
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write("Comenzile facute intre " + start + "-" + fin + "\n");
            writer.write("\n");
            writer.write(wrr.toString()+"\n");
            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        System.out.println("Comenzile facute intre " + start + "-" + fin + "\n");
        System.out.println(wrr.toString()+"\n");

    }
    public void raport2(int nr){
        assert nr>=0;

        Map<MenuItem,Long> nrLista;
        List<List<MenuItem>> lista=new ArrayList<>(orders.values());
        List<MenuItem> listaFin=  lista.stream().flatMap(x->x.stream()).collect(Collectors.toList());
        nrLista=listaFin.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        Map<MenuItem,Long> rezultat=nrLista.entrySet().stream().filter(x->x.getValue()>=nr).collect(Collectors
        .toMap(map->map.getKey(),map->map.getValue()));

        try{
            File file=new File("Raport2.txt");
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write("Produsele comandate de mai mult de "+nr+" ori: \n");
            System.out.println("Produsele comandate de mai mult de "+nr+" ori: \n");
            System.out.println(rezultat.toString()+"\n");
            writer.write(rezultat.toString());
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public void raport3(int nr,int minVal){

        Set<Order> keySet=orders.keySet();
        Stream<Order> stream=keySet.stream();
        Set<Order> wrr=stream.map(order -> order)
                .filter(val->(val.getTotal()<minVal)).collect(Collectors.toSet());

        Set<Order> toWr=wrr.stream().filter(f->(Collections.frequency(wrr,f)>nr)).distinct()
                .collect(Collectors.toSet());


        File file=new File("Raport3.txt");
        try {
            file.createNewFile();
            FileWriter writer=new FileWriter(file);
            writer.write("Cel mai mult s-a comandat la mesele: \n");
            System.out.println("Cel mai mult s-a comandat la mesele: \n");
          for(Order o:toWr){
              System.out.println(o.getTable()+" ");
              writer.write(o.getTable()+" ");
          }
            writer.write(" Si au avut o comanda mai mare de "+minVal);
            System.out.println(" Si au avut o comanda mai mare de "+minVal);
            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    public  void raport4(String zi){

        Map<Order,List<MenuItem>> result=orders.entrySet().stream().filter(x->x.getKey().getData().equals(zi))
                .collect(Collectors.toMap(map->map.getKey(),map->map.getValue()));
       // System.out.println(result);

        List<List<MenuItem>> lista=new ArrayList<>(result.values());
        List<MenuItem> list=lista.stream().flatMap(x->x.stream()).collect(Collectors.toList());
        Map<MenuItem,Long> nrLista;
        nrLista=list.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));

       File file=new File("Raport4.txt");
        try {
            file.createNewFile();
            FileWriter writer=new FileWriter(file);
            writer.write("Produsele comandate in data de "+zi+":");
            System.out.println("Produsele comandate in data de "+zi+":");
            writer.write(nrLista+"\n");
            writer.write("\n");
            writer.write("\n");
            System.out.println("\n");
            System.out.println(nrLista+"\n");
            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
