package tn.espritclubs.cinquieme_SAE_sept.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class User {
    public static final ArrayList<User> userArrayList = new ArrayList<User>();

    private int id;
    private String email;
    private String firstname;
    private String lastname;
    private String gender;
    private String password;
    private String role;
    private Date birthday;
    private String phoneNumber;
    private String aboutMe;
    private String profilePicture;

    public User(int id, String email, String firstname, String lastname, String gender, String password, String role, Date birthday, String phoneNumber, String aboutMe, String profilePicture) {
        this.id = id;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.password = password;
        this.role = role;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.aboutMe = aboutMe;
        this.profilePicture = profilePicture;
    }

    public User(int id, String email, String firstname, String lastname, String gender, String password, String role) {
        this.id = id;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.password = password;
        this.role = role;
    }

    public static User getUserForEmail(String passedEmail) {
        for(User user : userArrayList)
        {
            if(Objects.equals(user.getEmail(), passedEmail))
                return user;
        }
        return null;
    }
    public static User getUserForPhone(String passedPhone) {
        for(User user : userArrayList)
        {
            if(Objects.equals(user.getPhoneNumber(), passedPhone))
                return user;
        }
        return null;
    }

    public static User getUserForId(int passedUserId) {
        for(User user : userArrayList)
        {
            if(user.getId() == passedUserId)
                return user;
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", gender='" + gender + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", birthday=" + birthday +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", aboutMe='" + aboutMe + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                '}';
    }
}
