package com.frasato.user_service.domain.model;

public class User {
    private String id;
    private String name;
    private String phone;
    private String document;
    private String address;
    private String password;

    public User(){}

    public User(String id, String name, String phone, String document, String address, String password) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.document = document;
        this.address = address;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void validateDocument(){
        if(document.isBlank()) throw new RuntimeException("Document can't be empty");
    }
}
