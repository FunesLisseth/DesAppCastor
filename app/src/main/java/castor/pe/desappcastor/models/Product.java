package castor.pe.desappcastor.models;

/**
 * Created by Omar on 16/07/2016.
 */

public class Product {

    private int id;
    private int categoryId;
    private String name;
    private String description;
    private String brand;
    private String format;
    private String formatUnit;
    private String thickness;
    private String thicknessUnit;
    private int stock;
    private double localPrice;
    private double dolarPrice;
    private String offerFlag;
    private String imagePath;
    private String state;
    private String createdBy;
    private long createdAt;
    private String categoryName;
    private int parentCategoryId;
    private String parentCategoryName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getFormatUnit() {
        return formatUnit;
    }

    public void setFormatUnit(String formatUnit) {
        this.formatUnit = formatUnit;
    }

    public String getThickness() {
        return thickness;
    }

    public void setThickness(String thickness) {
        this.thickness = thickness;
    }

    public String getThicknessUnit() {
        return thicknessUnit;
    }

    public void setThicknessUnit(String thicknessUnit) {
        this.thicknessUnit = thicknessUnit;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getLocalPrice() {
        return localPrice;
    }

    public void setLocalPrice(double localPrice) {
        this.localPrice = localPrice;
    }

    public double getDolarPrice() {
        return dolarPrice;
    }

    public void setDolarPrice(double dolarPrice) {
        this.dolarPrice = dolarPrice;
    }

    public String getOfferFlag() {
        return offerFlag;
    }

    public void setOfferFlag(String offerFlag) {
        this.offerFlag = offerFlag;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(int parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public String getParentCategoryName() {
        return parentCategoryName;
    }

    public void setParentCategoryName(String parentCategoryName) {
        this.parentCategoryName = parentCategoryName;
    }
}
