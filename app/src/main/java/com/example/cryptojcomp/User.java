package com.example.cryptojcomp;

public class User {
    String username,id,address,balance,ccn;

    public User() {
    }

    public User(String username, String id, String address, String balance, String ccn) {
        this.username = username;
        this.id = id;
        this.address = address;
        this.balance = balance;
        this.ccn = ccn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getCcn() {
        return ccn;
    }

    public void setCcn(String ccn) {
        this.ccn = ccn;
    }
}
