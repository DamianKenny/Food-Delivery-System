package com.example.project.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "CustomerFeedback")
public class Review {
    @Id
    private String id;
    private String userId;
    private int Rating;
    private String message;

    // Getters and setters (required for Spring Data)
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public int getRating() { return Rating; }
    public void setRating(int Rating) { this.Rating = Rating; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
