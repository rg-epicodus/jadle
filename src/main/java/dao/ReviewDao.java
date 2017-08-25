package dao;


import models.Review;

import java.util.List;

public interface ReviewDao {

    //create
    void add (Review review);

    //read
    Review findById(int id);
    List<Review> getAllReviewsByRestaurant(int restaurantId);

    //update
    void update(String writtenBy, int rating, int restaurantId, int id, String content);

    //destroy
    void deleteById(int id);
}