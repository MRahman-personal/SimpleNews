package com.example.simplenews.Database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ArticleEntity {

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "description")
    public String description;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "url_string")
    public String url;

    @ColumnInfo(name = "url_to_image")
    public String urlToImage;

    @ColumnInfo(name = "published_at")
    public String publishedAt;

    public ArticleEntity(String title, String description, String url, String urlToImage, String publishedAt) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

}
