package bussinessLayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public abstract class MenuItem implements Serializable {
    private static final long serialVersionUID = -3241770291249316584L;

    public String nume;
    public float rating;
    public float calorii;
    public float proteine;
    public float grasimi;
    public float sodiu;
    public float pret;
    public int nrComenzi=0;

    public MenuItem(String nume,float rating,float calories,float protein,float fat,float sodium,float price){
        this.nume=nume;
        this.rating=rating;
        this.calorii=calories;
        this.proteine=protein;
        this.grasimi=fat;
        this.sodiu=sodium;
        this.pret=price;
    }

    public MenuItem() {

    }

    float computePrice() {
        return 0;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getNume() {
        return nume;
    }

    public float getPret() {
        return pret;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public float getCalorii() {
        return calorii;
    }

    public float getGrasimi() {
        return grasimi;
    }

    public float getProteine() {
        return proteine;
    }

    public float getSodiu() {
        return sodiu;
    }

    public void setCalorii(float calorii) {
        this.calorii = calorii;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public void setGrasimi(float grasimi) {
        this.grasimi = grasimi;
    }

    public void setProteine(float proteine) {
        this.proteine = proteine;
    }

    public void setSodiu(float sodiu) {
        this.sodiu = sodiu;
    }

    public abstract List<MenuItem> getCompositeProducts();

    public Vector<String[] > getData (){
        throw new UnsupportedOperationException();
    }
    public List<MenuItem> getItems(){
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "nume='" + nume + '\'' +
                ", rating=" + rating +
                ", calorii=" + calorii +
                ", proteine=" + proteine +
                ", grasimi=" + grasimi +
                ", sodiu=" + sodiu +
                ", pret=" + pret +
                '}';
    }

    public int getNrComenzi() {
        return nrComenzi;
    }

    public void setNrComenzi(int nrComenzi) {
        this.nrComenzi = nrComenzi;
    }
}
