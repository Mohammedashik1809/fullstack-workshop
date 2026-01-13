package collections_streams;

public class OptionalTest {

    public static void main(String[] args) {

        UserRepository repo = new UserRepository();
        User user = new User();

        Address addr = new Address();
        addr.setApartment("A-101");
        user.setAddress(addr);

        repo.save(1L, user);

        String apartment = repo.findById(1L)
                .flatMap(User::getAddress)
                .flatMap(Address::getApartment)
                .orElse("N/A");

        System.out.println(apartment);
    }
}
