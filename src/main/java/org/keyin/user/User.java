package org.keyin.user;

import org.mindrot.jbcrypt.BCrypt;

/**
    This is the parent class for all users, There are 3 types of users: Trainer, Member, and Admin.
*/
public class User {
    //Instance Fields
    /**
        The display name in which the user is to be referred to as.
    */
    private String username;

    /**
        The user's chosen password. The value is hashed automatically upon assignment.
    */
    private String password;

    /**
        The email address associated with this user.
    */
    private String email;

    /**
        A string of ten digits that represents the user's associated phone number. This value is formatted upon assignment.
    */
    private String phoneNumber;

    /**
        The address at which this user currently resides.
     */
    private String address;

    /**
        A string whose value can only be either "Admin", "Trainer", or "Member".
    */
    private String role;


    //Constructor
    /**
        The User class' only constructor, because all values herein are required to be defined.
    */
    public User(String username, String password, String email, String phoneNumber, String address, String role) throws IllegalArgumentException {
        this.username = username;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());

        if (email.contains("@")) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("An invalid email format was passed to User's constructor.");
        }

        if (phoneNumber.matches("\\d{10}")) {
            this.phoneNumber = String.format("(%s) %s-%s", phoneNumber.substring(0, 3), phoneNumber.substring(3, 7), phoneNumber.substring(7));
        } else {
            throw new IllegalArgumentException("An invalid phone number format was passed to User's constructor.");
        }

        this.address = address;

        if (role.equalsIgnoreCase("admin") || role.equalsIgnoreCase("trainer") || role.equalsIgnoreCase("member")) {
            this.role = role.toLowerCase();
        } else {
            throw new IllegalArgumentException("An invalid role value was passed to User's constructor.");
        }
    }


    //Instance Methods
    /**
        Presents this instance of the User class as a string for display.
    */
    public String toString() {
        return String.format("User[\n   username: %s,\n   password: %s,\n   email: %s,\n   phoneNumber: %s\n   role: %s\n]", this.username, this.password, this.email, this.phoneNumber, this.role);
    }


    //Getter Methods
    /**
        Retrieves this User's username.
    */
    public String getUsername() {
        return this.username;
    }

    /**
        Retrieves this User's hashed password.
    */
    public String getPassword() {
        return this.password;
    }

    /**
        Retrieves this User's email.
    */
    public String getEmail() {
        return this.email;
    }

    /**
        Retrieves this User's phone number.
    */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
        Retrieves this User's address.
    */
    public String getAddress() {
        return this.address;
    }

    /**
        Retrieves this User's role.
    */
    public String getRole() {
        return this.role;
    }


    //Setter Methods
    /**
        Assigns this User's username to the value provided.
    */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
        Assigns this User's password to the provided value's hash.
    */
    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
        Assigns this User's email to the provided value.
    */
    public void setEmail(String email) throws IllegalArgumentException {
        if (email.contains("@")) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("An invalid email format was passed to User class' email setter.");
        }
    }

    /**
        Assigns and formats this User's phone number to the provided string of ten digits.
    */
    public void setPhoneNumber(String phoneNumber) throws IllegalArgumentException {
        if (phoneNumber.matches("\\d{10}")) {
            this.phoneNumber = String.format("(%s) %s-%s", phoneNumber.substring(0, 3), phoneNumber.substring(3, 7), phoneNumber.substring(7));
        } else {
            throw new IllegalArgumentException("An invalid phone number format was passed to User class' phoneNumber setter.");
        }
    }

    /**
        Assigns this User's address to the provided value.
    */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
        Assigns and formats this User's phone number to the provided string of ten digits.
    */
    public void setRole(String role) throws IllegalArgumentException {
        if (role.equalsIgnoreCase("admin") || role.equalsIgnoreCase("trainer") || role.equalsIgnoreCase("member")) {
            this.role = role.toLowerCase();
        } else {
            throw new IllegalArgumentException("An invalid role value was passed to User class' role setter.");
        }
    }
}