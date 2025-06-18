public abstract class Person {
    protected int id;
    protected String name;
    protected String phoneNumber;

    public Person(int id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }// constructor

    
    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
    return "Citizen: " + name + ", Phone: " + phoneNumber;
}

}
