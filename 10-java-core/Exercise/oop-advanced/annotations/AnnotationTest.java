package annotations;

import java.util.List;

public class AnnotationTest {

    public static void main(String[] args) {

        User user = new User();
        user.setAge(15); // invalid age
        // name not set → null

        List<String> errors = Validator.validate(user);

        System.out.println("Validation Errors:");
        for (String error : errors) {
            System.out.println("- " + error);
        }
    }
}
