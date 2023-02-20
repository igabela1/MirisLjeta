package ba.unsa.etf.rpr.Domain;
import ba.unsa.etf.rpr.Domain.Idable;

import java.util.Objects;

public class User implements Idable {

    private int id;
    private String email;
    private String username;
    private String password;
    private String lastName;
    private int isAdministrator;
    private String firstName;

    public User(int id, String email, String username, String password, int isAdministrator) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.isAdministrator = isAdministrator;
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && isAdministrator == user.isAdministrator && Objects.equals(email, user.email) && Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, username, password, isAdministrator);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + isAdministrator +
                '}';
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



    public void setisAdministrator(int isAdministrator) {
        this.isAdministrator = isAdministrator;
    }


    public String getFirstName() {
        return firstName;
    }



    public String getLastName() {
        return lastName;
    }

    public int getisAdministrator() {
        return isAdministrator;
    }
}

