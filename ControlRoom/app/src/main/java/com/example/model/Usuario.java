package com.example.model;

public class Usuario {
    private String nomeUser;
    private String emailUser;
    private int id;

    public String getNomeUser() {
        return nomeUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNomeUser(String nomeUser) {
        this.nomeUser = nomeUser;

    }
}
