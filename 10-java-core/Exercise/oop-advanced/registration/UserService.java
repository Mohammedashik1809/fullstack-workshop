package registration;

import java.util.HashSet;
import java.util.Set;

public class UserService {

    private static final Set<String> registeredEmails = new HashSet<>();

    public void register(User user)
            throws ValidationException, DuplicateUserException {

        var errors = user.validate();
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }

        if (registeredEmails.contains(user.getEmail())) {
            throw new DuplicateUserException("Email already registered");
        }

        // Simulate DB save
        registeredEmails.add(user.getEmail());
    }

    public User findByEmail(String email) throws DatabaseException {
        try (DummyResource res = new DummyResource()) {
            if (!registeredEmails.contains(email)) {
                return null;
            }
            return new User("Dummy", email, "Dummy123", 20);
        } catch (Exception e) {
            throw new DatabaseException("Database access error");
        }
    }

    // Dummy resource to demonstrate try-with-resources
    private static class DummyResource implements AutoCloseable {
        public void close() {}
    }
}
