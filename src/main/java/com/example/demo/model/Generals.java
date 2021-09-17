package com.example.demo.model;

import javax.persistence.*;

@Entity
public class Generals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String avatar;
    @ManyToOne
    private Species species;
    private String description;

    public Generals() {
    }

    public Generals(Long id, String name, String avatar, Species species, String description) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.species = species;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
