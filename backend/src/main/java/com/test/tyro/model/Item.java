package com.test.tyro.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//Only mutable field is rating
@Entity
@Table(name="item")
public class Item {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double rating;

    private String api_ID;

    private LocalDate creation_date;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    public Item(double rating, String api_ID, LocalDate creation_date) {
        this.rating = rating;
        this.api_ID = api_ID;
        this.creation_date = creation_date;
    }

    public Item(Item item, User user){
        this(item.rating, item.api_ID, item.creation_date);
        this.user = user;
    }


    protected Item(){

    }

    public Long getId() {
        return id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getApi_ID() {
        return api_ID;
    }

    public LocalDate getCreation_date() {
        return creation_date;
    }

    public User getUser() {
        return user;
    }
}
