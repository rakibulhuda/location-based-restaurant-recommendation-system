package sqa.project.back_end.models;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class Restaurant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurantId;
    private String restaurantName;
    private double restaurantDistance;
    private String cuisineType;
    private double restaurantRating;
    private int restaurantRank;
    private double deliveryTips;
    private int estimatedDeliveryTime;
    private boolean isPickup;

    public Restaurant(){}

    public Restaurant(Long restaurantId, String restaurantName, double distance, String cuisineType, double rating, int rank, double deliveryTips, int estimatedDeliveryTime, boolean isPickup) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.restaurantDistance = distance;
        this.cuisineType = cuisineType;
        this.restaurantRating = rating;
        this.restaurantRank = rank;
        this.deliveryTips = deliveryTips;
        this.estimatedDeliveryTime = estimatedDeliveryTime;
        this.isPickup = isPickup;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public double getRestaurantDistance() {
        return restaurantDistance;
    }

    public void setRestaurantDistance(double restaurantDistance) {
        this.restaurantDistance = restaurantDistance;
    }

    public String getRestaurantCuisineType() {
        return cuisineType;
    }

    public void setRestaurantCuisineType(String restaurantCuisineType) {
        this.cuisineType = restaurantCuisineType;
    }

    public double getRestaurantRating() {
        return restaurantRating;
    }

    public void setRestaurantRating(double restaurantRating) {
        this.restaurantRating = restaurantRating;
    }

    public int getRestaurantRank() {
        return restaurantRank;
    }

    public void setRestaurantRank(int rank) {
        this.restaurantRank = rank;
    }

    public double getDeliveryTips() {
        return deliveryTips;
    }

    public void setDeliveryTips(double tips) {
        this.deliveryTips = tips;
    }

    public int getEstimatedDeliveryTime() {
        return estimatedDeliveryTime;
    }

    public void setEstimatedDeliveryTime(int estimatedDeliveryTime) {
        this.estimatedDeliveryTime = estimatedDeliveryTime;
    }

    public boolean isPickup() {
        return isPickup;
    }

    public void setPickup(boolean pickup) {
        isPickup = pickup;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurantId=" + restaurantId +
                ", restaurantName='" + restaurantName + '\'' +
                ", distance=" + restaurantDistance +
                ", cuisineType='" + cuisineType + '\'' +
                ", rating=" + restaurantRating +
                ", rank=" + restaurantRank +
                ", deliveryTips=" + deliveryTips +
                ", estimatedDeliveryTime=" + estimatedDeliveryTime +
                ", isPickup=" + isPickup +
                '}';
    }
}
