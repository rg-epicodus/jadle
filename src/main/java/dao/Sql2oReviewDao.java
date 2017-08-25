package dao;


import models.Review;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oReviewDao implements ReviewDao{

    private final Sql2o sql2o;
    public Sql2oReviewDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Review review){
        String sql = "INSERT INTO reviews (writtenBy, rating, restaurantId, content) VALUES (:writtenBy, :rating, :restaurantId, :content)";
        try (Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql)
                    .bind(review)
                    .executeUpdate()
                    .getKey();
            review.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Review> getAllReviewsByRestaurant(int restaurantId){
        try (Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM reviews WHERE restaurantId = :restaurantId")
                    .addParameter("restaurantId", restaurantId)
                    .executeAndFetch(Review.class);
        }
    }

    @Override
    public Review findById(int id){
        try (Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM reviews WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Review.class);
        }
    }

    @Override
    public void update(String writtenBy, int rating, int restaurantId, int id, String content){
        String sql = "UPDATE reviews SET (writtenBy, rating, restaurantId, content) = (:writtenBy, :rating, :restaurantId, :content) WHERE id = :id";
        try (Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("writtenBy", writtenBy)
                    .addParameter("rating", rating)
                    .addParameter("restaurantId", restaurantId)
                    .addParameter("content", content)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.print(ex);
        }

    }

    @Override
    public void deleteById(int id) {
        try (Connection con = sql2o.open()){
            con.createQuery("DELETE FROM reviews WHERE id = :id")
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.print(ex);
        }
    }
}