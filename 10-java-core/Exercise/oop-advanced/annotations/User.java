package annotations;

public class User {

    @NotNull(message = "Name is required")
    private String name;

    @Validate(min = 18, max = 100, message = "Age must be 18-100")
    private int age;

    public User() {}

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getters & setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
