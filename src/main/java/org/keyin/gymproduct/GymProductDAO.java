package org.keyin.gymproduct;
import java.sql.*;
import java.util.*;

import org.keyin.database.DatabaseConnection;

public class GymProductDAO {
    /**
     * Initialize database table on first use
     */
    public GymProductDAO() {
        createTableIfNotExists();
        initializeSampleDataIfEmpty();
    }

    /**
     * Create the gym_products table if it doesn't exist
     */
    private void createTableIfNotExists() {
        String createTableSQL = """
            CREATE TABLE IF NOT EXISTS gym_products (
                product_id SERIAL PRIMARY KEY,
                product_name VARCHAR(100) NOT NULL,
                category VARCHAR(50) NOT NULL,
                price DECIMAL(10,2) NOT NULL,
                stock_quantity INTEGER NOT NULL,
                description TEXT
            )
            """;

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(createTableSQL);
            System.out.println("Database table 'gym_products' ready.");

        } catch (SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
        }
    }

    /**
     * Add sample data only if table is empty
     */
    private void initializeSampleDataIfEmpty() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM gym_products");
            rs.next();

            if (rs.getInt(1) == 0) {
                addProduct(new GymProduct("Protein Powder", "workout-gear", 45.99, 25, "Whey protein powder"));
                addProduct(new GymProduct("Water Bottle", "drinks", 12.99, 50, "BPA-free water bottle"));
                addProduct(new GymProduct("Energy Bar", "food", 3.99, 100, "High-protein energy bar"));
                addProduct(new GymProduct("Gym Towel", "workout-gear", 15.99, 30, "Microfiber gym towel"));
                addProduct(new GymProduct("Sports Drink", "drinks", 2.99, 75, "Electrolyte sports drink"));
                addProduct(new GymProduct("Pre-workout Supplement", "workout-gear", 29.99, 20, "Energy boost supplement"));
                System.out.println("Sample data added to database.");
            }

        } catch (SQLException e) {
            System.err.println("Error checking/initializing data: " + e.getMessage());
        }
    }

    /**
     *
     * CREATE - Add new product
     */
    public static boolean addProduct(GymProduct product) {
        String sql = """
            INSERT INTO gym_products (product_name, category, price, stock_quantity, description)
            VALUES (?, ?, ?, ?, ?)
            """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, product.getProductName());
            pstmt.setString(2, product.getCategory());
            pstmt.setDouble(3, product.getPrice());
            pstmt.setInt(4, product.getStockQuantity());
            pstmt.setString(5, product.getDescription());

            if (pstmt.executeUpdate() > 0) {
                ResultSet keys = pstmt.getGeneratedKeys();
                if (keys.next()) {
                    product.setProductId(keys.getInt(1));
                }
                return true;
            }

        } catch (SQLException e) {
            System.err.println("Error adding product: " + e.getMessage());
        }

        return false;
    }

    /**
     * READ - Get all products
     */
    public static List<GymProduct> getAllProducts() {
        List<GymProduct> products = new ArrayList<>();
        String sql = "SELECT * FROM gym_products ORDER BY product_id";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                GymProduct product = new GymProduct(
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getString("category"),
                        rs.getDouble("price"),
                        rs.getInt("stock_quantity"),
                        rs.getString("description")
                );
                products.add(product);
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving products: " + e.getMessage());
        }

        return products;
    }

    /**
     * READ - Get product by ID
     */
    public static GymProduct getProductById(int productId) {
        String sql = "SELECT * FROM gym_products WHERE product_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, productId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new GymProduct(
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getString("category"),
                        rs.getDouble("price"),
                        rs.getInt("stock_quantity"),
                        rs.getString("description")
                );
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving product: " + e.getMessage());
        }

        return null;
    }

    /**
     * UPDATE - Update product price
     */
    public static boolean updateProductPrice(int productId, double newPrice) {
        String sql = "UPDATE gym_products SET price = ? WHERE product_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, newPrice);
            pstmt.setInt(2, productId);

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error updating price: " + e.getMessage());
            return false;
        }
    }
}

