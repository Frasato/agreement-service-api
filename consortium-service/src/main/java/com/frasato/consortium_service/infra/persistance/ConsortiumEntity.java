package com.frasato.consortium_service.infra.persistance;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "consortium")
public class ConsortiumEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String description;
    private Integer price;
    private Boolean active;
    private Instant includedAt;
    @Version
    private Long version;

    public ConsortiumEntity() {}

    public ConsortiumEntity(String id, String name, String description, Integer price, Boolean active, Instant includedAt) {
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

    public Long getVersion() {return version;}

    public void setVersion(Long version) {this.version = version;}

    @PrePersist
    private void setDate(){
        this.setIncludedAt(Instant.now());
    }
}