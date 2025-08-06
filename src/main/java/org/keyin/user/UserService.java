package org.keyin.user;

import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.SQLException;

public class UserService {
    //Login Method
    public User loginForUser(String username, String password) throws SQLException, AssertionError {
        User potentialUser = UserDAO.selectUserByUsername(username);

        assert potentialUser != null; //"assert" keyword kind of acts like a shorter if statement, it's similar to Jest's "expect()" function.
        if (BCrypt.checkpw(password, potentialUser.getPassword())) {
            //if (potentialUser.getRole().equals())

            return potentialUser;
        } else {
            return null;
        }
    }


    //Register Method
    public void registerUser(User userToRegister) throws SQLException, IOException {
        UserDAO.insertNewUser(userToRegister);
    }
}