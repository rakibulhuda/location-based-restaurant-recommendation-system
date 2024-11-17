package sqa.project.back_end.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sqa.project.back_end.models.Restaurant;
import sqa.project.back_end.service.RestaurantService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/all")
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Long id) {
        Optional<Restaurant> restaurant = restaurantService.getRestaurantById(id);
        return restaurant.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/pickup")
    public List<Restaurant> getPickupRestaurants() {
        return restaurantService.getPickupRestaurants();
    }

    @GetMapping("/eat-out")
    public List<Restaurant> getEatOutRestaurants() {
        return restaurantService.getEatOutRestaurants();
    }

    @PostMapping("register")
    public ResponseEntity<?> registerRestaurants(@RequestBody Restaurant restaurant){
        try {
            restaurantService.registerRestaurants(restaurant);
            return new ResponseEntity<>(restaurant, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Long id, @RequestBody Restaurant restaurantDetails) {
        Optional<Restaurant> restaurant = restaurantService.getRestaurantById(id);
        if (restaurant.isPresent()) {
            Restaurant updatedRestaurant = restaurant.get();
            updatedRestaurant.setRestaurantName(restaurantDetails.getRestaurantName());
            updatedRestaurant.setRestaurantRating(restaurantDetails.getRestaurantRating());
            updatedRestaurant.setRestaurantCuisineType(restaurantDetails.getRestaurantCuisineType());
            updatedRestaurant.setRestaurantDistance(restaurantDetails.getRestaurantDistance());
            updatedRestaurant.setRestaurantRank(restaurantDetails.getRestaurantRank());
            updatedRestaurant.setDeliveryTips(restaurantDetails.getDeliveryTips());
            updatedRestaurant.setEstimatedDeliveryTime(restaurantDetails.getEstimatedDeliveryTime());
            updatedRestaurant.setPickup(restaurantDetails.isPickup());
            return ResponseEntity.ok(restaurantService.saveRestaurant(updatedRestaurant));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        Optional<Restaurant> restaurant = restaurantService.getRestaurantById(id);
        if (restaurant.isPresent()) {
            restaurantService.deleteRestaurant(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/reserve")
    public ResponseEntity<Void> makeReservation(@PathVariable Long id) {
        boolean reserved = restaurantService.makeReservation(id);
        return reserved ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/pickup")
    public ResponseEntity<Void> pickupOrder(@PathVariable Long id) {
        boolean pickedUp = restaurantService.pickupOrder(id);
        return pickedUp ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/sorted/rank")
    public List<Restaurant> getRestaurantsSortedByRank() {
        return restaurantService.getRestaurantsSortedByRank();
    }

    @GetMapping("/sorted/tips")
    public List<Restaurant> getRestaurantsSortedByDeliveryTips() {
        return restaurantService.getRestaurantsSortedByDeliveryTips();
    }

    @GetMapping("/sorted/delivery-time")
    public List<Restaurant> getRestaurantsSortedByEstimatedDeliveryTime() {
        return restaurantService.getRestaurantsSortedByEstimatedDeliveryTime();
    }

    @GetMapping("/sorted/distance")
    public List<Restaurant> getRestaurantsSortedByDistance() {
        return restaurantService.getRestaurantsSortedByDistance();
    }

    @GetMapping("/by-cuisine/{cuisineType}")
    public List<Restaurant> getRestaurantsByCuisineType(@PathVariable String cuisineType) {
        return restaurantService.getRestaurantsByCuisineType(cuisineType);
    }
}
