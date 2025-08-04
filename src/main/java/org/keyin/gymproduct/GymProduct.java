package org.keyin.gymproduct;
import java.io.IOException;
import java.util.Objects;

public class GymProduct {

    private int productId;
    private String productName;
    private String category;
    private double price;
    private int stockQuantity;
    private String description;

    /**
     * Default Constructor
     */
    public GymProduct() {
    }

    /**
     * Constructor with parameters
     */
    public GymProduct(String productName, String category, double price, int stockQuantity, String description) {
        this();
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.description = description;
    }

    /**
     * Full Constructor
     */
    public GymProduct(int productId, String productName, String category, double price, int stockQuantity, String description) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.description = description;
    }

    /**
     * Getter and Setter classes.
     */
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
