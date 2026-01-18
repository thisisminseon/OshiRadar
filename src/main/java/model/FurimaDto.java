package model;

import java.util.Date;

public class FurimaDto {

    private int postId;
    private int userId;
    private String title;
    private String description;
    private int price;
    private String imagePath;
    private Date createdAt;
    private char status;

    public int getPostId() {
        return postId;
    }
    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
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

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public char getStatus() {
        return status;
    }
    public void setStatus(char status) {
        this.status = status;
    }
}