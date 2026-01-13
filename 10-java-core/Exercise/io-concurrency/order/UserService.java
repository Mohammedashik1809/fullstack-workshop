package io_concurrency;

import java.util.concurrent.CompletableFuture;

public class UserService {

    public CompletableFuture<User> getUser(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            sleep(500);
            return new User(id, "User-" + id);
        });
    }

    private static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }
}
