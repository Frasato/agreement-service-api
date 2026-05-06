package com.frasato.consortium_service.domain.model;

import java.time.Instant;

public class Consortium {
    private String id;
    private String name;
    private String description;
    private Integer price;
    private Boolean active;
    private Instant includedAt;

    public Consortium() {}

    public Consortium(String id, String name, String description, Integer price, Boolean active, Instant includedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.active = active;
        this.includedAt = includedAt;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Instant getIncludedAt() {
        return includedAt;
    }

    public void setIncludedAt(Instant includedAt) {
        this.includedAt = includedAt;
    }

    public void validateName(){
        if(this.name.isEmpty()) throw new RuntimeException("Name can't be empty");
    }

    public void validatePrice(){
        if(this.price <= 0) throw new RuntimeException("Price can't be negative or zero");
    }
}
