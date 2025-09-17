/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package murach.business;

import java.io.Serializable;

/**
 *
 * @author ADMIN
 */
public class User implements Serializable {

    private String firstName;
    private String lastName;
    private String email;

    public User() {
        firstName = "";
        lastName = "";
        email = "";
    }

    public User(String fN, String lN, String email) {
        this.firstName = fN;
        this.lastName = lN;
        this.email = email;
    }

    public void setFirstName(String fN) {
        firstName = fN;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setLastName(String lN) {
        lastName = lN;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }
}
