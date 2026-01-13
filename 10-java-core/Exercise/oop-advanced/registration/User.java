package registration;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class User {

    private String name;
    private String email;
    private String password;
    private int age;

    public User(String name, String email, String password, int age) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
    }

    public List<String> validate() {
        List<String> errors = new ArrayList<>();

        if (name == null || name.length() < 2 || name.length() > 50) {
            errors.add("Name too short");
        }

        if (email == null || !Pattern.matches(
                "^[A-Za-z0-9+_.-]+@(.+)$", email)) {
            errors.add("Invalid email");
        }

        if (password == null ||
                password.length() < 8 ||
                !password.matches(".*[A-Z].*") ||
                !password.matches(".*\\d.*")) {
            errors.add("Password too weak");
        }

        if (age < 13) {
            errors.add("Must be 13+");
        }

        return errors;
    }

    public String getEmail() {
        return email;
    }
}

