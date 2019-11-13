package com.example.demo.entity;

import java.io.Serializable;

/**
 * @author GuoJingyuan
 * @date 2019/9/18
 */
public class User implements Serializable {
    public User(String name, String email, String account, String password) {
        this.name = name;
        this.email = email;
        this.account = account;
        this.password = password;
    }

    public User() {
    }

    private String name;
    private String email;
    private String account;
    private String password;


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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
