package collections_streams;

import java.util.*;

public class UserRepository {

    private Map<Long, User> store = new HashMap<>();

    public Optional<User> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    public void save(Long id, User user) {
        store.put(id, user);
    }
}
