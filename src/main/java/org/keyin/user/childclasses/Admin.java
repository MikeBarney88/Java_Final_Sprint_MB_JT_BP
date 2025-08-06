package org.keyin.user.childclasses;

import org.keyin.gymproduct.GymProduct;
import org.keyin.gymproduct.GymProductDAO;
import org.keyin.gymproduct.GymProductService;
import org.keyin.user.User;
import org.keyin.user.UserDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * User account with the role of "admin" belong to this childclass, granting them the required privileges.
 */
public class Admin extends User {
    /**
     * The one and only constructor for the Admin childclass of User.
     */
    public Admin(String username, String password, String email, String phoneNumber, String address) throws IllegalArgumentException, SQLException {
        super(username, password, email, phoneNumber, address, "admin");
    }


    //Role-Specific Methods
    /**
     * Allows this Admin User to retrieve all Users' information currently held within the database.
     */
    public ArrayList<User> getAllUsers() throws SQLException {
        return UserDAO.selectAllUsers();
    }

    /**
     * Allows this Admin User to retrieve any User's information by querying the database with the provided username.
     *
     * @param username The username to search for in the database.
     */
    public User getUser(String username) throws SQLException {
        return UserDAO.selectUserByUsername(username);
    }

    /**
     * Displays all currently active gym memberships and their cumulative annual revenue.
     */
    //public ArrayList<Membership> getAllMemberships() {}

    /**
     * Allows this Admin User to define a new product to be sold.
     */
    public void createProduct(String productName, String category, double price, int stockQuantity, String description) {
        GymProductService.addNewItem(GymProductService.UserRole.ADMIN, productName, category, price, stockQuantity, description);
    }

    /**
     * Lets this Admin User edit the price of an existing GymProduct.
     */
    public boolean setPrice(int productID, double newPrice) {
        return GymProductService.setPrice(GymProductService.UserRole.ADMIN, productID, newPrice);
    }

    /**
     * Displays to the Admin User every item currently in stock and their cumulative value.
     */
    public void showAllProducts() {
        GymProductService.printStockReport(GymProductService.UserRole.ADMIN);
    }
}