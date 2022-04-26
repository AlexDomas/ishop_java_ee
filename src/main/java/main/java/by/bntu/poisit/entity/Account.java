package main.java.by.bntu.poisit.entity;

import main.java.by.bntu.poisit.model.CurrentAccount;

public class Account extends AbstractEntity<Integer> implements CurrentAccount {

    private String name;
    private String email;
    private int role;

    public Account() {
        super();
    }

    public Account(String name, String email) {
        super();
        this.name = name;
        this.email = email;
    }
    
    public Account(String name, String email, int role) {
        super();
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String getDescription() {
        return name + "(" + email + ")";
    }

    @Override
    public String toString() {
        return String.format("Account [id=%s, name=%s, email=%s]", getId(), name, email);
    }

    
}
