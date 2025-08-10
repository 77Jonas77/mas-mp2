import java.io.Serializable;
import java.util.regex.Pattern;

public class Contact implements Serializable {
    private String email;
    private String phoneNumber;
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern emailPattern = Pattern.compile(EMAIL_REGEX);

    private static final String PHONE_REGEX = "^\\+\\d{2}\\d{9}$";
    private static final Pattern phonePattern = Pattern.compile(PHONE_REGEX);

    public Contact(String email, String phoneNumber) {
        setEmail(email);
        setPhoneNumber(phoneNumber);
    }

    public Contact(String email) {
        setEmail(email);
    }

    public void setEmail(String email) {
        if(!isEmailValid(email)){
            throw new IllegalArgumentException("Email is not valid");
        }
        this.email = email;
    }

    private boolean isEmailValid(String email) { // to pewnie mogloby byc w jakims validatorze jako statyczna metoda klasowa
        return email!=null && emailPattern.matcher(email).matches();
    }

    public void setPhoneNumber(String phoneNumber) {
        if(!isPhoneNumberValid(phoneNumber)){
            throw new IllegalArgumentException("Phone number is not valid");
        }
        this.phoneNumber = phoneNumber;
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        return phoneNumber != null && phonePattern.matcher(phoneNumber).matches();
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
