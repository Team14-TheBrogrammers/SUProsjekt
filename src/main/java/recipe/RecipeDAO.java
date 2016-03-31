package recipe;

import com.google.common.collect.ImmutableList;
import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        int recipeId = 0;
        int ingredientId = 0;
        try {
            PreparedStatement pStatement = connection.prepareStatement(
                    "INSERT INTO recipe(recipe_id, recipe_name) VALUES (NULL, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            pStatement.setString(1, recipeName);
            pStatement.execute();
            ResultSet resultSet = pStatement.getGeneratedKeys();
            if (resultSet.next()) {
                recipeId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        for (Ingredient ingredient : ingredients) {
            try {
                PreparedStatement pStatement = connection.prepareStatement(
                        "INSERT INTO ingredients(ingredient_id, ingredient_name) VALUES (NULL, ?)",
                        Statement.RETURN_GENERATED_KEYS
                );
                pStatement.setString(1, ingredient.getIngredientName());
                pStatement.execute();
                ResultSet resultSet = pStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    ingredientId = resultSet.getInt(1);
                }
                try {
                    PreparedStatement pStatement2 = connection.prepareStatement(
                            "INSERT INTO recipe_ingredients(recipe_id, ingredient_id, quantity) VALUES (?,?,?)"
                    );
                    pStatement2.setInt(1, recipeId);
                    pStatement2.setInt(2, ingredientId);
                    pStatement2.setString(3, ingredient.getQuantity());
                    pStatement2.execute();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            } catch (SQLException e) {
                System.out.println(e);
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
