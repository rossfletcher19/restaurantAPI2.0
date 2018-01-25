package dao;

import models.Foodtype;
import org.sql2o.Sql2o;

import java.util.List;

public class Sql2oFoodtypeDAO implements FoodtypeDAO{

    private final Sql2o sql2o;

    public Sql2oFoodtypeDAO(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Foodtype foodtype) {

    }

//    @Override
//    public List<Foodtype> getAll() {
//        return null;
//    }
//
//    @Override
//    public void deleteById(int id) {
//
//    }
}
