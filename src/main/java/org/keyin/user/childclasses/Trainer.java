package org.keyin.user.childclasses;

import org.keyin.gymproduct.GymProductService;
import org.keyin.user.User;
import java.sql.SQLException;

/**
 * The extension of the User class that represents gym trainers who can schedule classes that members will attend.
 */
public class Trainer extends User {
    /**
     * The one and only constructor for the Trainer childclass of User.
     */
    public Trainer(String username, String password, String email, String phoneNumber, String address) throws IllegalArgumentException, SQLException {
        super(username, password, email, phoneNumber, address, "trainer");
    }


    //Role-Specific Methods
    /**
     * Allows the Trainer to view all available products for sale at the gym.
     */
    public void showAllProducts() {
        GymProductService.viewAllProducts();
    }
}