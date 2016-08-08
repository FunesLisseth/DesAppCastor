package castor.pe.desappcastor.models;

import java.util.ArrayList;
import java.util.List;

public class Offer {

    private int id;
    private int image;
    private String title;
    private String price;

    private boolean isGroupHeader = false;

    public Offer(String title) {
    }
    public Offer(int id, int image, String title, String price) {
        super();
        this.id = id;
        this.image = image;
        this.title = title;
        this.price = price;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getImage() {
        return image;
    }
    public void setImage(int image) {
        this.image = image;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getPrice() {
        return this.price;
    }

}
