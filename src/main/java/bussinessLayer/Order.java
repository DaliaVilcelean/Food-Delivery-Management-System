package bussinessLayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Order implements Serializable {

    private static final long serialVersionUID = 6366944309499208249L;

    private String data;
    private int ora;
    private int table;
    private int orderId;
    float total;


    public Order(int orderId,String data,int ora,int table){
        this.orderId=orderId;
        this.ora=ora;
        this.data=data;
        this.table=table;
    }
    public Order(){

    }
    public int getHours(){
        return ora;
    }

    public int getTable() {
        return table;
    }

    public void setTable(int table) {
        this.table = table;
    }

    public int getOra() {
        return ora;
    }

    public void setOra(int ora) {
        this.ora = ora;
    }

    public String getData() {
        return data;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderId() {
        return orderId;
    }

    public float getTotal(){
        return total;
    }



    public void setData(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object obj) {
        Order o=(Order) obj;
        if(this.orderId==o.orderId){
            if(this.data.equals(o.data)){
                    return true;
            }
        }
       return false;
    }

    @Override
    public int hashCode() {
        return ((orderId+table)*99)/13;
    }

    @Override
    public String toString() {
        return "Order{" +
                "data='" + data + '\'' +
                ", ora=" + ora +
                ", table=" + table +
                ", orderId=" + orderId +
                '}'+"\n";
    }

    public void setTotal(float pret) {
        this.total=pret;
    }
}
