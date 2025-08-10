import java.time.LocalDate;

public class Person extends ObjectExtension {
    private String name;
    private String lastName;
    private LocalDate birthDate;
    private Contact contact;

    public Person(String name, String lastName, LocalDate birthDate, Contact contact) {
        try{
            setName(name);
            setLastName(lastName);
            setBirthDate(birthDate);
            setContact(contact);
        } catch (IllegalArgumentException e) {
            removeFromExtent();
            throw e;
        }
    }

    public void setName(String name) {
        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be blank or empty");
        }
        this.name = name;
    }

    public void setLastName(String lastName) {
        if(lastName !=null && lastName.isBlank()) {
            throw new IllegalArgumentException("Last name cannot be blank or empty");
        }
        this.lastName = lastName;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        if(birthDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Birth date cannot be in the future");
        }
    }

    public int calculateAge() {
        return LocalDate.now().getYear() - birthDate.getYear();
    }

    public int calculateAge(LocalDate date) {
        return Math.abs(date.getYear() - birthDate.getYear());
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Contact getContact() {
        return contact;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", contact=" + contact +
                '}';
    }
}
