package org.keyin.user.childclasses;

import org.keyin.user.User;
import org.keyin.workoutclasses.WorkoutClass;
import org.keyin.workoutclasses.WorkoutClassDAO;

import java.sql.SQLException;

/**
 * This extension of the User class is meant to represent those who register as clients.
 */
public class Member extends User {
    /**
     * The one and only constructor for the Member childclass of User.
     */
    public Member(String username, String password, String email, String phoneNumber, String address) throws IllegalArgumentException, SQLException {
        super(username, password, email, phoneNumber, address, "member");
    }


    //Role-Specific Methods
    /**
     * Will allow the member to view a list of the currently available WorkoutClasses.
     */
    public void viewWorkoutClasses() {
        //List<WorkoutClass> workoutClassList = WorkoutClassDAO.selectAllClasses() or something idk man

        //for (WorkoutClass woClass : workoutClassList) {
            //System.out.printf("%s\n", woClass)
        //}
    }

    /**
     * Retrieves and displays this Member's total cost spent on Memberships.
     */
    public void viewTotalMembershipExpenses() {
        //MembershipDAO.getUserByID(this.id) or something maybe
    }

    /**
     * Purchases a new gym Membership for the Member.
     */
    public void buyMembership() {
        //MembershipService.buyNewMembership(this.id) or something.
    }

    /**
     * Allows Member to view
     */
}