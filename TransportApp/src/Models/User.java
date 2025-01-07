package Models;


public class User {
    private String name;
    private String surname;
    private String username;
    private String gender;
    private int phoneNumber;
    private String password;

    public User(String name,String surname,String username,String gender,int phoneNumber,String password) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getUsername() {
        return username;
    }

    public String getGender() {
        return gender;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }
}
