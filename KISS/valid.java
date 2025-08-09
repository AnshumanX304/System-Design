package KISS;

// valid.java
import java.util.List;

class UserService {

    // Simple and clear way to check if a username exists
    public boolean doesUserExist(List<String> users, String username) {
        return users.stream().anyMatch(user -> user.trim().equalsIgnoreCase(username.trim()));
    }
}

public class valid {
    public static void main(String[] args) {
        UserService service = new UserService();
        List<String> users = List.of("Alice", "Bob", "Charlie");

        System.out.println(service.doesUserExist(users, "bob")); // true
    }
}
