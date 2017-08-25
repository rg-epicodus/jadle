package dao;

import models.Foodtype;
import models.Restaurant;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class Sql2oFoodtypeDao implements FoodtypeDao{

    private final Sql2o sql2o;

    public Sql2oFoodtypeDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Foodtype foodtype) {
        String sql = "INSERT INTO foodtypes (name) VALUES (:name)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql)
                    .bind(foodtype)
                    .executeUpdate()
                    .getKey();
            foodtype.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Foodtype> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM foodtypes")
                    .executeAndFetch(Foodtype.class);
        }
    }

    @Override
    public Foodtype findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM foodtypes where id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Foodtype.class);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from foodtypes WHERE id=:id";
        String joinQuery = "DELETE from restaurants_foodtypes WHERE foodtypeid = :foodtypeId";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
            con.createQuery(joinQuery)
                    .addParameter("foodtypeId", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

//    @Override
//    public List<Restaurant> getAllRestaurantsForAFoodtype(int foodtypeId) {
//        List<Restaurant> restaurants = new ArrayList<>();
//        return restaurants;
//    }

    @Override
    public void addFoodtypeToRestaurant(Restaurant restaurant, Foodtype foodtype){
        String sql = "INSERT INTO restaurants_foodtypes (restaurantid, foodtypeid) VALUES (:restaurantId, :foodtypeId)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("restaurantId", restaurant.getId())
                    .addParameter("foodtypeId", foodtype.getId())
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<Restaurant> getAllRestaurantsForAFoodtype(int foodtypeId) {

        ArrayList<Restaurant> restaurants = new ArrayList<>();

        String joinQuery = "SELECT restaurantid FROM restaurants_foodtypes WHERE foodtypeid = :foodtypeId";

        try (Connection con = sql2o.open()) {
            List<Integer> allRestaurantIds = con.createQuery(joinQuery)
                    .addParameter("foodtypeId", foodtypeId)
                    .executeAndFetch(Integer.class); //what is happening in the lines above?
            for (Integer restaurantId : allRestaurantIds){
                String restaurantQuery = "SELECT * FROM restaurants WHERE id = :restaurantId";
                restaurants.add(
                        con.createQuery(restaurantQuery)
                                .addParameter("restaurantId", restaurantId)
                                .executeAndFetchFirst(Restaurant.class));
            }
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
        return restaurants;
    }

//        @Override
//    public List<Restaurant> getRestaurantsByFoodtype(int foodtypeId) {
//        try(Connection con = sql2o.open()){
//            return con.createQuery("SELECT * FROM restaurants WHERE foodtypeId = :foodtypeId")
//                    .addParameter("foodtypeId", foodtypeId)
//                    .executeAndFetch(Restaurant.class);
//        }
//    }



}

