package org.keyin.user.childclasses;

import org.keyin.gymproduct.GymProductService;
import org.keyin.user.User;
import org.keyin.workoutclasses.WorkoutClass;
import org.keyin.workoutclasses.WorkoutClassService;

import java.io.IOException;
import java.sql.SQLException;

/**
 * This extension of the User class is meant to represent those who register as clients.
 */
public class Member extends User {
    /**
     * The one and only constructor for the Member childclass of User.
     */
    public Member(String username, String password, String email, String phoneNumber, String address) throws IllegalArgumentException, SQLException, IOException {
        super(username, password, email, phoneNumber, address, "member");
    }


    //Role-Specific Methods
    /**
     * Allows the Member to view all available products for sale at the gym.
     */
    public void viewAllProducts() {
        GymProductService.viewAllProducts();
    }
}