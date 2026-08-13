package com.example.lab7.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String genre;
    private String platform;
    private Double rating;
    private LocalDate releaseDate;
    private Double price;
    private String discountType; 

    // Constructors
    public Game() {
    }

    public Game(String title, String genre, String platform, Double rating, LocalDate releaseDate, Double price, String discountType) {
        this.title = title;
        this.genre = genre;
        this.platform = platform;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.price = price;
        this.discountType = discountType;
    }

    @Transient
    public Double getFinalPrice() {
        if (this.price == null) return 0.0;
        if (this.discountType == null) return this.price;

        switch (this.discountType.toUpperCase()) {
            case "STUDENT":
                return this.price * 0.90; // ส่วนลด 10%
            case "SEASONAL":
                return this.price * 0.80; // ส่วนลด 20%
            default:
                return this.price; // ราคาปกติ
        }
    }

    

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDiscountName() {
        if (this.discountType == null) {
            return "ราคาปกติ (ไม่มีส่วนลด)";
        }

        return switch (this.discountType.toUpperCase()) {
            case "STUDENT", "ส่วนลดนักศึกษา (10%)" -> "ส่วนลดนักศึกษา (10%)";
            case "SEASONAL", "ส่วนลดเทศกาล (20%)" -> "ส่วนลดเทศกาล (20%)";
            default -> "ราคาปกติ (ไม่มีส่วนลด)";
        };
    }

    public void setDiscountName(String discountName) {
        this.discountType = discountName;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public double calculateFinalPrice() {
        return 0.0; 
    }
}