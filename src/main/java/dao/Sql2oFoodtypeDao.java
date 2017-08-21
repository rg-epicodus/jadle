package dao;

import org.sql2o.Sql2o;

public class Sql2oFoodtypeDao implements FoodtypeDao {
    private final Sql2o sql2o;

    // constructor
    public Sql2oFoodtypeDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    //getters and setters



}
