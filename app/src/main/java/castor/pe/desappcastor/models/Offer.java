package castor.pe.desappcastor.models;

import java.util.ArrayList;
import java.util.List;

public class Offer {

    private int id;
    private String imagePath;
    private String name;
    private String localPrice;

    private boolean isGroupHeader = false;

    public Offer(String title) {
    }
    public Offer(int id, String imagePath, String name, String localPrice) {
        super();
        this.id = id;
        this.imagePath = imagePath;
        this.name = name;
        this.localPrice = localPrice;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLocalPrice() {
        return this.localPrice;
    }

}
