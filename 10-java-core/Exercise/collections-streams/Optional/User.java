package collections_streams;

import java.util.Optional;

public class User {

    private String middleName;
    private Address address;

    public Optional<String> getMiddleName() {
        return Optional.ofNullable(middleName);
    }

    public Optional<Address> getAddress() {
        return Optional.ofNullable(address);
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
