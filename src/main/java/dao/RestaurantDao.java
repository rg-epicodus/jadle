package dao;

import models.Restaurant;

public interface RestaurantDao {

    //create
    void add (Restaurant restaurant); //J
    // void addRestaurantToFoodType(Restaurant restaurant, Foodtype foodtype) //D & E

    //read
//    List<Restaurant> getAll(); //A
    // List<Foodtype> getAllFoodtypesForARestaurant(int restaurantId); //D & E - we will implement this soon.

//    Restaurant findById(int id); //B & C

    //update
//    void update(int id, String name, String address, String zipcode, String phone, String website, String email, String image); //L

    //delete
//    void deleteById(int id); //K
}
