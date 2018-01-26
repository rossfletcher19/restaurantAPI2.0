package dao;

import models.Foodtype;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oFoodtypeDAO implements FoodtypeDAO{

    private final Sql2o sql2o;

    public Sql2oFoodtypeDAO(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Foodtype name) {
        String sql = "INSERT INTO  foodtypes (name) VALUES (:name)";
        try (Connection con = sql2o.open()) {
            int foodtypeId = (int) con.createQuery(sql)
                    .bind(name)
                    .executeUpdate()
                    .getKey();
            name.setId(foodtypeId);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
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
