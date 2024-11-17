package sqa.project.back_end.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sqa.project.back_end.models.Restaurant;
import sqa.project.back_end.repository.RestaurantRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    public void registerRestaurants(Restaurant restaurant){
        if(restaurantRepository.existsByRestaurantName(restaurant.getRestaurantName())){
            throw new RuntimeException("Restaurant Already existed");
        }
        restaurantRepository.save(restaurant);
        System.out.println(restaurant);
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Optional<Restaurant> getRestaurantById(Long id) {
        return restaurantRepository.findById(id);
    }

    public List<Restaurant> getPickupRestaurants() {
        return restaurantRepository.findByIsPickup(true);
    }

    public List<Restaurant> getEatOutRestaurants() {
        return restaurantRepository.findByIsPickup(false);
    }

    public Restaurant saveRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }

    public boolean makeReservation(Long id) {
        Optional<Restaurant> restaurant = getRestaurantById(id);
        if (restaurant.isPresent() && !restaurant.get().isPickup()) {
            return true;
        }
        return false;
    }

    public boolean pickupOrder(Long id) {
        Optional<Restaurant> restaurant = getRestaurantById(id);
        if (restaurant.isPresent() && restaurant.get().isPickup()) {
            return true;
        }
        return false;
    }

    public List<Restaurant> getRestaurantsSortedByRank() {
        return restaurantRepository.findAll().stream()
                .sorted(Comparator.comparingInt(Restaurant::getRestaurantRank))
                .toList();
    }

    public List<Restaurant> getRestaurantsSortedByDeliveryTips() {
        return restaurantRepository.findAll().stream()
                .sorted((r1, r2) -> Double.compare(r2.getDeliveryTips(), r1.getDeliveryTips()))
                .toList();
    }

    public List<Restaurant> getRestaurantsSortedByEstimatedDeliveryTime() {
        return restaurantRepository.findAll().stream()
                .sorted(Comparator.comparingInt(Restaurant::getEstimatedDeliveryTime))
                .toList();
    }

    public List<Restaurant> getRestaurantsSortedByDistance() {
        return restaurantRepository.findAll().stream()
                .sorted(Comparator.comparingDouble(Restaurant::getRestaurantDistance))
                .toList();
    }

    public List<Restaurant> getRestaurantsByCuisineType(String cuisineType) {
        return restaurantRepository.findByCuisineType(cuisineType);
    }

}
