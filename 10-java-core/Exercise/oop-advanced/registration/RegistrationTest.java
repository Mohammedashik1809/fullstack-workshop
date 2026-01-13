package registration;

public class RegistrationTest {

    public static void main(String[] args) {

        UserController controller = new UserController();

        // Validation error
        Result result1 =
                controller.register("J", "invalid-email", "weak", 10);
        System.out.println(result1);

        // Duplicate email
        controller.register("John", "existing@email.com", "Strong1Pass", 25);
        Result result2 =
                controller.register("John", "existing@email.com", "Strong1Pass", 25);
        System.out.println(result2);

        // Successful registration
        Result result3 =
                controller.register("Alice", "alice@email.com", "Secure123", 30);
        System.out.println(result3);
    }
}
