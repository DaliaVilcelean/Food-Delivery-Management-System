package bussinessLayer;

import java.util.ArrayList;
import java.util.List;

public class BaseProduct extends MenuItem{

    private static final long serialVersionUID = -277638266862226496L;

    public BaseProduct(String nume, float rating,float calorii,float proteine,float grasimi,float sodiu,float pret) {
        super(nume,rating,calorii,proteine,grasimi,sodiu,pret);
    }

public BaseProduct(){
    super();

}


    //  @Override
    float computePrice() {
        return super.getPret();
    }

    public List<MenuItem> getCompositeProducts(){
        return null;
    }
}
