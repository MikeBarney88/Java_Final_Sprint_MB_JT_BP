package org.keyin.user;

import org.keyin.user.childclasses.Admin;
import org.keyin.user.childclasses.Member;
import org.keyin.user.childclasses.Trainer;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.SQLException;

public class UserService {
    //Login Method
    public User loginForUser(String username, String password) throws SQLException, AssertionError, IOException {
        User potentialUser = UserDAO.selectUserByUsername(username);


        assert potentialUser != null; //"assert" keyword kind of acts like a shorter if statement, it's similar to Jest's "expect()" function.
        if (BCrypt.checkpw(password, potentialUser.getPassword())) {
            switch (potentialUser.getRole()) {
                case "admin":
                    return new Admin(potentialUser.getID(), potentialUser.getUsername(), potentialUser.getPassword(), potentialUser.getEmail(), potentialUser.getPhoneNumber(), potentialUser.getAddress());
                case "trainer":
                    return new Trainer(potentialUser.getID(), potentialUser.getUsername(), potentialUser.getPassword(), potentialUser.getEmail(), potentialUser.getPhoneNumber(), potentialUser.getAddress());
                case "member":
                    return new Member(potentialUser.getID(), potentialUser.getUsername(), potentialUser.getPassword(), potentialUser.getEmail(), potentialUser.getPhoneNumber(), potentialUser.getAddress());
                default:
                return potentialUser;
            }
        } else {
            return null;
        }
    }


    //Register Method
    public void registerUser(User userToRegister) throws SQLException, IOException {
        UserDAO.insertNewUser(userToRegister);
    }
}