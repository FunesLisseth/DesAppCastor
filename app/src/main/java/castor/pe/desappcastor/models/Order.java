package castor.pe.desappcastor.models;

import java.util.List;

/**
 * Created by Omar on 8/08/2016.
 */
public class Order {


    /**
     * id : 1
     * sellerId : 1
     * clientId : 1
     * usedFlag : 0
     * state : 1
     * createdBy : ADMIN
     * createdAt : 1469525940000
     * orderProducts : [{"id":1,"orderId":1,"product":{"id":3,"categoryId":2,"name":"Aglomerado Crudo","description":"nuevo","brand":"MDP","format":"2.14 x 2.44","formatUnit":"mts","thickness":"4/6/8/9/12/15/18/25/30","thicknessUnit":"mm","stock":50,"localPrice":45.59,"dolarPrice":13.03,"offerFlag":"0","imagePath":"\\images\\aglomerado\\mdp.jpg","state":"1","createdBy":"ADMIN","createdAt":1468946280000},"quantity":2,"state":"1","createdBy":"ADMIN","createdAt":1469525940000},{"id":2,"orderId":1,"product":{"id":5,"categoryId":2,"name":"Aglomerado Crudo Tropical","description":"nuevo","brand":"DURAPLAC","format":"2.15 x 2.44","formatUnit":"mts","thickness":"19","thicknessUnit":"mm","stock":50,"localPrice":45.59,"dolarPrice":13.03,"offerFlag":"1","imagePath":"\\images\\aglomerado\\duraplac2.jpg","state":"1","createdBy":"ADMIN","createdAt":1468946280000},"quantity":5,"state":"1","createdBy":"ADMIN","createdAt":1469525940000}]
     */

    private int id;
    private int sellerId;
    private int clientId;
    private String usedFlag;
    private String state;
    private String createdBy;
    private long createdAt;
    /**
     * id : 1
     * orderId : 1
     * product : {"id":3,"categoryId":2,"name":"Aglomerado Crudo","description":"nuevo","brand":"MDP","format":"2.14 x 2.44","formatUnit":"mts","thickness":"4/6/8/9/12/15/18/25/30","thicknessUnit":"mm","stock":50,"localPrice":45.59,"dolarPrice":13.03,"offerFlag":"0","imagePath":"\\images\\aglomerado\\mdp.jpg","state":"1","createdBy":"ADMIN","createdAt":1468946280000}
     * quantity : 2
     * state : 1
     * createdBy : ADMIN
     * createdAt : 1469525940000
     */

    private List<OrderProductsBean> orderProducts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getUsedFlag() {
        return usedFlag;
    }

    public void setUsedFlag(String usedFlag) {
        this.usedFlag = usedFlag;
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

    public List<OrderProductsBean> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProductsBean> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public static class OrderProductsBean {
        private int id;
        private int orderId;
        /**
         * id : 3
         * categoryId : 2
         * name : Aglomerado Crudo
         * description : nuevo
         * brand : MDP
         * format : 2.14 x 2.44
         * formatUnit : mts
         * thickness : 4/6/8/9/12/15/18/25/30
         * thicknessUnit : mm
         * stock : 50
         * localPrice : 45.59
         * dolarPrice : 13.03
         * offerFlag : 0
         * imagePath : \images\aglomerado\mdp.jpg
         * state : 1
         * createdBy : ADMIN
         * createdAt : 1468946280000
         */

        private ProductBean product;
        private int quantity;
        private String state;
        private String createdBy;
        private long createdAt;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public ProductBean getProduct() {
            return product;
        }

        public void setProduct(ProductBean product) {
            this.product = product;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
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

        public static class ProductBean {
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
        }
    }
}
