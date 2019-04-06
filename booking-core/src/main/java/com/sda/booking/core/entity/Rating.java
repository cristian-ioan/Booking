package com.sda.booking.core.entity;

import com.sda.booking.core.base.BaseEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ratings", schema = "booking")
public class Rating extends BaseEntity {

    @OneToOne
    @JoinColumn(name="client_id")
    private Client client;

    @Column(name = "comment", length = 40, nullable = false)
    private String comment;

    @OneToOne
    @JoinColumn(name="property_id")
    private Property property;

    @Column(name = "rating", length = 40, nullable = false)
    private int rating;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rating)) return false;
        Rating rating1 = (Rating) o;
        return getRating() == rating1.getRating() &&
                Objects.equals(getClient(), rating1.getClient()) &&
                Objects.equals(getComment(), rating1.getComment()) &&
                Objects.equals(getProperty(), rating1.getProperty());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClient(), getComment(), getProperty(), getRating());
    }

    @Override
    public String toString() {
        return "Rating{" +
                "client=" + client +
                ", comment='" + comment + '\'' +
                ", property=" + property +
                ", rating=" + rating +
                '}';
    }
}
