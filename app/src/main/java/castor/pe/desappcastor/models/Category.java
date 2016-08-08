package castor.pe.desappcastor.models;

import java.util.List;

/**
 * Created by Omar on 4/08/2016.
 */
public class Category {

    private int id;
    private String name;
    private String description;
    private int state;
    private String createdBy;
    private String createdAt;
    private int subCategoriesCount;

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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getSubCategoriesCount() {
        return subCategoriesCount;
    }

    public void setSubCategoriesCount(int subCategoriesCount) { this.subCategoriesCount = subCategoriesCount; }
}
