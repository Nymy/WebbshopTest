package bo;

import db.PersonDB;
import java.util.Collection;

public class Person {
    private String fname;
    private String lname;
    private int postcode;
    private String username;
    private String password;
static public Collection searchUser(String u, String p){
    return PersonDB.searchUser(u,p);
}
    public Person(String fname, String lname, int postcode, String username, String password) {
        this.fname = fname;
        this.lname = lname;
        this.postcode = postcode;
        this.username = username;
        this.password = password;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public int getPostcode() {
        return postcode;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Person{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", postcode=" + postcode +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
