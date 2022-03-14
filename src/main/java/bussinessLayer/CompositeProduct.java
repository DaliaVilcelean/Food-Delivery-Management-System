package bussinessLayer;



import java.util.ArrayList;
import java.util.List;

public class CompositeProduct extends MenuItem{
    private static final long serialVersionUID = 6214990430094306267L;

    private static List<MenuItem> compositeProducts;

    public CompositeProduct(String nume,List<MenuItem> compositeProducts) {
        super();
        this.compositeProducts=compositeProducts;
      compositeProducts=new ArrayList<MenuItem>();
    }
    public CompositeProduct() {
        super();
        compositeProducts=new ArrayList<MenuItem>();
    }

   // @Override
    float computePrice() {
        float sumPret=0;
        for(MenuItem m:compositeProducts){
            sumPret+=m.getPret();
        }
        return sumPret;
    }

    public void computeAll(MenuItem newItem){
        compositeProducts.add(newItem);

        float newRating=(newItem.getRating()+super.getRating())/2;
        float newCalorii=(newItem.getCalorii()+super.getCalorii());
        float newProteine=newItem.getProteine()+super.getProteine();
        float newGrasimi=newItem.getGrasimi()+super.getGrasimi();
        float newSodiu=newItem.getSodiu()+super.getSodiu();
        float newPret=newItem.getPret()+super.getPret();
        super.setCalorii(newCalorii);
        super.setGrasimi(newGrasimi);
        super.setPret(newPret);
        super.setProteine(newProteine);
        super.setRating(newRating);
        super.setSodiu(newSodiu);
    }


    @Override
    public List<MenuItem> getCompositeProducts() {
        return compositeProducts;
    }

    public void setCompositeProducts(List<MenuItem> compositeProducts) {
        this.compositeProducts = compositeProducts;
    }
}
