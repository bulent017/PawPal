package com.bulentoral.pawpal.model;

import java.util.Date;

public class Post {
    private String postId;
    private String userId; // İlanı veren kullanıcı
    private String animalId; // İlgili hayvanın ID'si
    private PostType postType; // Sahiplendirme veya Kayıp
    private String title;
    private String description;
    private Date datePosted;
    private boolean status;   // ilan durumu aktif ,güncel
    private enum PostType {
        ADOPTION, // Sahiplendirme için
        LOST // Kayıp hayvan için
    }

    public Post(String postId, String userId, String animalId, PostType postType, String title, String description, Date datePosted, boolean status) {
        this.postId = postId;
        this.userId = userId;
        this.animalId = animalId;
        this.postType = postType;
        this.title = title;
        this.description = description;
        this.datePosted = datePosted;
        this.status = status;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAnimalId() {
        return animalId;
    }

    public void setAnimalId(String animalId) {
        this.animalId = animalId;
    }

    public PostType getPostType() {
        return postType;
    }

    public void setPostType(PostType postType) {
        this.postType = postType;
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

    public Date getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
