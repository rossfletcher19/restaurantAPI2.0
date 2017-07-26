package models;

import java.time.LocalDateTime;

/**
 * Created by epicodus_staff on 7/25/17.
 */
public class Review {

    private String writtenBy;
    private int rating;
    private LocalDateTime createdAt;
    private int id;


    public Review(String writtenBy, int rating) {
        this.writtenBy = writtenBy;
        this.rating = rating;
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review = (Review) o;

        if (rating != review.rating) return false;
        if (id != review.id) return false;
        return writtenBy.equals(review.writtenBy);
    }

    @Override
    public int hashCode() {
        int result = writtenBy.hashCode();
        result = 31 * result + rating;
        result = 31 * result + id;
        return result;
    }
}
