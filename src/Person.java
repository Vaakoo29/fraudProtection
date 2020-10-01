public class Person {

    private String fullName;
    private String dateOfBirth;

    Person(String fullName, String dateOfBirth) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
    }

    public void setFullName(String value) {
        fullName = value;
    }

    public void setDateOfBirth(String value) {
        dateOfBirth = value;
    }

    public String toString() {
        return fullName + " " + dateOfBirth;
    }

}
