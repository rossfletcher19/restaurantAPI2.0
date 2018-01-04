package models;

import java.time.LocalDateTime;

/**
 * Created by epicodus_staff on 7/25/17.
 */
public class Review {

    private int id;
    private String writtenBy;
    private int rating;
    private String content;
    private int restaurantId;

    public Review(String writtenBy, int rating, String content, int restaurantId) {
        this.writtenBy = writtenBy;
        this.rating = rating;
        this.content = content;
        this.restaurantId = restaurantId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWrittenBy() {
        return writtenBy;
    }

    public void setWrittenBy(String writtenBy) {
        this.writtenBy = writtenBy;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review = (Review) o;

        if (rating != review.rating) return false;
        if (restaurantId != review.restaurantId) return false;
        if (!writtenBy.equals(review.writtenBy)) return false;
        return content.equals(review.content);
    }

    @Override
    public int hashCode() {
        int result = writtenBy.hashCode();
        result = 31 * result + rating;
        result = 31 * result + content.hashCode();
        result = 31 * result + restaurantId;
        return result;
    }
}
