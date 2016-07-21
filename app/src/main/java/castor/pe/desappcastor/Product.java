package castor.pe.desappcastor;

import android.graphics.drawable.Drawable;

/**
 * Created by Omar on 16/07/2016.
 */
public class Product {

    private int id;
    private int image;
    private String title;
    private String detail;

    private boolean isGroupHeader = false;

    public Product(String title) {
        this(-1, -1,title,null);
        isGroupHeader = true;
    }
    public Product(int id, int image, String title, String detail) {
        super();
        this.id = id;
        this.image = image;
        this.title = title;
        this.detail = detail;
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
    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
    public boolean isGroupHeader() {
        return isGroupHeader;
    }
    public void setGroupHeader(boolean isGroupHeader) {
        this.isGroupHeader = isGroupHeader;
    }
}
