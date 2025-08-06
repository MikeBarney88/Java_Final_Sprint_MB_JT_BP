package org.keyin.gymproduct;
import java.util.*;

public class GymProductService {

    // Injecting the DAO for GymProduct gives us access to the database operations
    // related to GymProduct entities.
    GymProductDAO gymProductDAO = new GymProductDAO();

    public enum UserRole {
        MEMBER, TRAINER, ADMIN
    }

    /**
     * MEMBER AND TRAINER SERVICES - View all products
     * Members can view all the products available at the gym to purchase
     * Trainers can view all the products like the Member
     */
    public static void viewAllProducts() {
        List<GymProduct> allProducts = GymProductDAO.getAllProducts();

        if (allProducts.isEmpty()) {
            System.out.println("No products available at this time.");
            return;
        }

        System.out.println();
        System.out.println("\n  Gym products available for purchase");

        for (GymProduct product : allProducts) {
            System.out.printf("%-25s | %-12s | $%-8.2f | Stock: %d\n",
                    product.getProductName(),
                    product.getCategory(),
                    product.getPrice(),
                    product.getStockQuantity());

            if (product.getDescription() != null && !product.getDescription().isEmpty()) {
                System.out.printf("  %s\n", product.getDescription());
            }
            System.out.println();
        }
    }

    /**
     * ADMIN SERVICES
     * Admins can add new items
     */
    public static boolean addNewItem(UserRole userRole, String name, String category, double price, int stock, String description) {
        if (userRole != UserRole.ADMIN) {
            System.out.println("Access denied. Admin privileges required.");
            return false;
        }

        if (name == null || name.trim().isEmpty()) {
            System.out.println("Product name cannot be empty");
            return false;
        }

        if (price < 0) {
            System.out.println("Price cannot be negative");
        }

        if (stock < 0) {
            System.out.println("Stock cannot be negative");
        }

        GymProduct newProduct = new GymProduct(name.trim(), category, price, stock, description);
        boolean success = GymProductDAO.addProduct(newProduct);

        if (success) {
            System.out.printf("New item '%s' added successfully!\n", name);
        } else {
            System.out.println("Failed to add new item.");
        }

        return success;
    }

    /**
     * ADMIN SERVICES
     * Admins can set prices
     */
    public static boolean setPrice(UserRole userRole, int productId, double newPrice) {
        if (userRole != UserRole.ADMIN) {
            System.out.println("Access denied. Admin privileges required.");
            return false;
        }

        if (newPrice < 0) {
            System.out.println("Price cannot be negative");
            return false;
        }

        GymProduct product = GymProductDAO.getProductById(productId);
        if (product == null){
            System.out.printf("Product with ID %d not found.\n", productId);
            return false;
        }

        double oldPrice = product.getPrice();
        boolean success = GymProductDAO.updateProductPrice(productId, newPrice);

        if (success) {
            System.out.printf("Price set for '%s': $%.2f to $%.2f\n", product.getProductName(), oldPrice, newPrice);
        } else {
            System.out.println("Failed to set price.");
        }
        return success;
    }

    /**
     * ADMIN SERVICES
     * Admins can print a report of all items in stock
     */
    public static void printStockReport(UserRole userRole) {
        if (userRole != UserRole.ADMIN) {
            System.out.println("Access denied. Admin privileges required.");
            return;
        }
        List<GymProduct> allProducts = GymProductDAO.getAllProducts();

        if (allProducts.isEmpty()) {
            System.out.println("No products in stock");
            return;
        }

        System.out.println();
        System.out.println("STOCK REPORT - ALL ITEMS");
        System.out.println();
        System.out.printf("%-3s | %-20s | %-12s | %-10s | %-4s\n",
                "ID", "Product Name", "Category", "Price", "Stock");
        System.out.println();

        for (GymProduct product : allProducts) {
            System.out.printf("%-3d | %-20s | %-12s | $%-9.2f | %-8d\n",
                    product.getProductId(),
                    product.getProductName(),
                    product.getCategory(),
                    product.getPrice(),
                    product.getStockQuantity());
        }
        System.out.println();
        System.out.printf("Total Items: %d\n", allProducts.size());

    }
}
