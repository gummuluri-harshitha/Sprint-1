public class Citizen extends Person {
    private String email;
    private String address;

    public Citizen(int id, String name, String phoneNumber, String email, String address) {
        super(id, name, phoneNumber);  // calls constructor of Person
        this.email = email;
        this.address = address;
    }

    // Getters
    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    // Optional: toString() for testing and printing
    @Override
    public String toString() {
        return "Citizen{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
