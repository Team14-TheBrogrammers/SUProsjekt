package recipe;

import com.google.common.collect.ImmutableList;
import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Nicole on 09.03.2016.
 * Data access object
 */

public class RecipeDAO {

    Connection connection;

    public RecipeDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean create(String recipeName, List<Ingredient> ingredients) {
        if( !addRecipe(recipeName) ) {
            return false;
        }
        if( !addIngredients(recipeName, ingredients) ) {
            return false;
        }
        return addInstructions(recipeName);
    }

    private boolean addInstructions(String recipeName) {
        return false;
    }

    private boolean addRecipe(String recipeName) {
        try {
            PreparedStatement pStatement = connection.prepareStatement(
                    "INSERT INTO recipe(recipe_name) VALUES (?)"
            );
            pStatement.setString(1, recipeName);
            pStatement.execute();
            return true;
        } catch(SQLException e) {
            System.err.println(e);
            return false;
        }

    }

    private boolean addIngredients(String recipeName, List<Ingredient> ingredients) {
        for (Ingredient ingredient : ingredients) {
            try {
                PreparedStatement pStatement = connection.prepareStatement(
                        "INSERT INTO ingredients(ingredient_name) VALUES (?)"
                );
                pStatement.setString(1, ingredient.getIngredientName());
                pStatement.execute();
            } catch (SQLException e) {
                System.out.println(e);
            }

            try {
                PreparedStatement pStatement = connection.prepareStatement(
                        "INSERT INTO recipe_ingredients(recipe_name, ingredient_name, quantity) VALUES (?,?,?)"
                );
                pStatement.setString(1, recipeName);
                pStatement.setString(2, ingredient.getIngredientName());
                pStatement.setString(3, ingredient.getQuantity());
                pStatement.execute();
            } catch (SQLException e) {
                System.err.println(e);
            }

        }
        return true;
    }

    public Recipe read() {
        return null;
    }

    public boolean update() {
        return false;
    }

    public boolean delete() {
        return false;
    }


}
