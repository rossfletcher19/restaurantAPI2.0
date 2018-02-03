package models;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class Review {

    private int id;
    private String writtenBy;
    private String content;
    private int rating;
    private int restaurantId;
    private long createdat;


    public Review(String writtenBy, String content, int rating, int restaurantId) {
        this.writtenBy = writtenBy;
        this.content = content;
        this.rating = rating;
        this.restaurantId = restaurantId;
        this.createdat = System.currentTimeMillis();
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

    public long getCreatedat() {
        return createdat;
    }

    public void setCreatedat() {
        this.createdat = System.currentTimeMillis();
    }

    public String getFormattedCreatedAt() {
        Date date = new Date(createdat);
        String datePatternToUse = "MM/dd/yyyy @ K:mm a";

        SimpleDateFormat sdf = new SimpleDateFormat(datePatternToUse);
        return sdf.format(date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review = (Review) o;

        if (rating != review.rating) return false;
        if (restaurantId != review.restaurantId) return false;
        if (id != review.id) return false;
        if (!writtenBy.equals(review.writtenBy)) return false;
        return content != null ? content.equals(review.content) : review.content == null;
    }

    @Override
    public int hashCode() {
        int result = writtenBy.hashCode();
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + rating;
        result = 31 * result + restaurantId;
        result = 31 * result + id;
        return result;
    }
}
