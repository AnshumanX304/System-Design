package KISS;
// invalid.java

import java.util.ArrayList;
import java.util.List;

class UserService {

    // Over-engineered way to check if a username exists in a list
    public boolean doesUserExist(List<String> users, String username) {
        List<String> matchedUsers = new ArrayList<>();
        for (String user : users) {
            if (user.trim().toLowerCase().equals(username.trim().toLowerCase())) {
                matchedUsers.add(user);
            }
        }
        return matchedUsers.size() > 0;
    }
}

public class invalid {
    public static void main(String[] args) {
        UserService service = new UserService();
        List<String> users = List.of("Alice", "Bob", "Charlie");

        System.out.println(service.doesUserExist(users, "bob")); // true
    }
}

