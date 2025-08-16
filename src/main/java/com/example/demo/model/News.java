package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ai_news_dataset") 
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    private String url;

    private String source;

    private String category; // <-- AI-generated category

    // --- Constructors ---
    public News() {
    }

    public News(Long id, String title, String description, String url, String source, String category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.url = url;
        this.source = source;
        this.category = category;
    }

    // --- Getters & Setters ---
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "News [id=" + id + ", title=" + title + ", description=" + description +
                ", url=" + url + ", source=" + source + ", category=" + category + "]";
    }
}
