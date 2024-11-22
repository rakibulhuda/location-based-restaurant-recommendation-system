package sqa.project.back_end.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sqa.project.back_end.models.Restaurant;
import sqa.project.back_end.repository.RestaurantRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestaurantServiceTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private RestaurantService restaurantService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllRestaurants() {
        Restaurant restaurant = new Restaurant(1L, "Test Restaurant", 5.0, "Italian", 4.5, 1, 2.0, 30, true);
        when(restaurantRepository.findAll()).thenReturn(Collections.singletonList(restaurant));

        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        assertEquals(1, restaurants.size());
        assertEquals("Test Restaurant", restaurants.get(0).getRestaurantName());
    }

    @Test
    void testGetAllRestaurantsWhenNoRestaurants() {
        when(restaurantRepository.findAll()).thenReturn(Collections.emptyList());

        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        assertTrue(restaurants.isEmpty());
    }

    @Test
    void testRegisterRestaurants() {
        Restaurant restaurant = new Restaurant(1L, "Test Restaurant", 5.0, "Italian", 4.5, 1, 2.0, 30, true);
        when(restaurantRepository.existsByRestaurantName("Test Restaurant")).thenReturn(false);
        when(restaurantRepository.save(restaurant)).thenReturn(restaurant);

        restaurantService.registerRestaurants(restaurant);
        verify(restaurantRepository, times(1)).save(restaurant);
    }

    @Test
    void testRegisterRestaurantsThrowsException() {
        Restaurant restaurant = new Restaurant(1L, "Test Restaurant", 5.0, "Italian", 4.5, 1, 2.0, 30, true);
        when(restaurantRepository.existsByRestaurantName("Test Restaurant")).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> restaurantService.registerRestaurants(restaurant));
        assertEquals("Restaurant Already existed", exception.getMessage());
    }

    @Test
    void testGetRestaurantById() {
        Restaurant restaurant = new Restaurant(1L, "Test Restaurant", 5.0, "Italian", 4.5, 1, 2.0, 30, true);
        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(restaurant));

        Optional<Restaurant> foundRestaurant = restaurantService.getRestaurantById(1L);
        assertTrue(foundRestaurant.isPresent());
        assertEquals("Test Restaurant", foundRestaurant.get().getRestaurantName());
    }

    @Test
    void testGetRestaurantByIdNotFound() {
        when(restaurantRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Restaurant> foundRestaurant = restaurantService.getRestaurantById(1L);
        assertFalse(foundRestaurant.isPresent());
    }

    @Test
    void testGetPickupRestaurants() {
        Restaurant restaurant = new Restaurant(1L, "Test Restaurant", 5.0, "Italian", 4.5, 1, 2.0, 30, true);
        when(restaurantRepository.findByIsPickup(true)).thenReturn(Collections.singletonList(restaurant));

        List<Restaurant> restaurants = restaurantService.getPickupRestaurants();
        assertEquals(1, restaurants.size());
        assertTrue(restaurants.get(0).isPickup());
    }

    @Test
    void testGetEatOutRestaurants() {
        Restaurant restaurant = new Restaurant(1L, "Test Restaurant", 5.0, "Italian", 4.5, 1, 2.0, 30, false);
        when(restaurantRepository.findByIsPickup(false)).thenReturn(Collections.singletonList(restaurant));

        List<Restaurant> restaurants = restaurantService.getEatOutRestaurants();
        assertEquals(1, restaurants.size());
        assertFalse(restaurants.get(0).isPickup());
    }

    @Test
    void testDeleteRestaurant() {
        Restaurant restaurant = new Restaurant(1L, "Test Restaurant", 5.0, "Italian", 4.5, 1, 2.0, 30, true);
        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(restaurant));

        restaurantService.deleteRestaurant(1L);
        verify(restaurantRepository, times(1)).deleteById(1L);
    }

    @Test
    void testMakeReservation() {
        Restaurant restaurant = new Restaurant(1L, "Test Restaurant", 5.0, "Italian", 4.5, 1, 2.0, 30, false);
        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(restaurant));

        boolean reserved = restaurantService.makeReservation(1L);
        assertTrue(reserved);
    }

    @Test
    void testMakeReservationFails() {
        Restaurant restaurant = new Restaurant(1L, "Test Restaurant", 5.0, "Italian", 4.5, 1, 2.0, 30, true);
        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(restaurant));

        boolean reserved = restaurantService.makeReservation(1L);
        assertFalse(reserved);
    }

    @Test
    void testPickupOrder() {
        Restaurant restaurant = new Restaurant(1L, "Test Restaurant", 5.0, "Italian", 4.5, 1, 2.0, 30, true);
        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(restaurant));

        boolean pickedUp = restaurantService.pickupOrder(1L);
        assertTrue(pickedUp);
    }

    @Test
    void testPickupOrderFails() {
        Restaurant restaurant = new Restaurant(1L, "Test Restaurant", 5.0, "Italian", 4.5, 1, 2.0, 30, false);
        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(restaurant));

        boolean pickedUp = restaurantService.pickupOrder(1L);
        assertFalse(pickedUp);
    }
}