package recipe;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;

/**
 * Created by Nicole on 09.03.2016.
 * Data access object
 */

public class RecipeDAO {

    public boolean create() {
        return false;
    }

    public Recipe read() {
        //TODO(nicoleubk): Replace with actual logic later
        return new Recipe(
                "Pancakes",
                ImmutableList.of("8 eggs", "1 cup of flour", "One teaspoon of salt"),
                ImmutableList.of("Mix batter", "Fry pancakes")
        );
    }


    public boolean update() {
        return false;
    }

    public boolean delete() {
        return false;
    }


}
