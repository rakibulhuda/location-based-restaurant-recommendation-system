package sqa.project.back_end.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sqa.project.back_end.models.Restaurant;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findByIsPickup(boolean isPickup);
    List<Restaurant> findByCuisineType(String cuisineType);
    boolean existsByRestaurantName(String restaurantName);
}
