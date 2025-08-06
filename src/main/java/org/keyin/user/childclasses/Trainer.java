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
     * Creates a new WorkoutClass that this Trainer will teach.
     */
    public void createWorkoutClass() {}

    /**
     * Updates the specified WorkoutClass (can only be classes this Trainer was appointed to teach).
     */
    public void updateWorkoutClass() {}

    /**
     * Deletes the specified WorkoutClass (can only be classes this Trainer was appointed to teach).
     */
    public void deleteWorkoutClass() {}

    /**
     * Will allow the Trainer to view a list of WorkoutClasses they're to teach.
     */
    public void viewAllAssignedClasses() {}

    /**
     * Retrieves and displays this Member's total cost spent on Memberships.
     */
    public void viewTotalMembershipExpenses() {}

    /**
     * Purchases a new gym Membership for the Trainer.
     */
    public void buyMembership() {}

    /**
     * Allows the Trainer to view all available products for sale at the gym.
     */
    public void viewAllProducts() {
        GymProductService.viewAllProducts();
    }
}