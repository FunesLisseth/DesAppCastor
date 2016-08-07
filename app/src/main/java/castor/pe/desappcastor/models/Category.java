package castor.pe.desappcastor.models;

import java.util.List;

/**
 * Created by Omar on 4/08/2016.
 */
public class Category {


    /**
     * id : 1
     * name : Tableros
     * description : Categoria Tableros
     * state : 1
     * createdBy : ADMIN
     * createdAt : 1468941180000
     * subCategoriesCount : 0
     */

    private int id;
    private String name;
    private String description;
    private int state;
    private int createdBy;
    private long createdAt;
    private int subCategoriesCount;
    private List<Category> items;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public int getSubCategoriesCount() {
        return subCategoriesCount;
    }

    public void setSubCategoriesCount(int subCategoriesCount) {
        this.subCategoriesCount = subCategoriesCount;
    }

    public List<Category> getItems() {
        return items;
    }

    public void setItems(List<Category> items) {
        this.items = items;
    }
}
