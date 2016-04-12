package recipe;

import com.google.common.collect.ImmutableList;
import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipeDAO {

    Connection connection;

    public RecipeDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean create(String recipeName, List<Ingredient> ingredients, List<Instruction> instructions) {
        if (!addRecipe(recipeName)) {
            return false;
        }
        if (!addIngredients(recipeName, ingredients)) {
            return false;
        }
        return addInstructions(recipeName, instructions);
    }

    private boolean addRecipe(String recipeName) {
        try {
            PreparedStatement pStatement = connection.prepareStatement(
                    "INSERT INTO recipe(recipe_name) VALUES (?)"
            );
            pStatement.setString(1, recipeName);
            pStatement.execute();
            return true;
        } catch (SQLException e) {
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

    private boolean addInstructions(String recipeName, List<Instruction> instructions) {
        for (Instruction instruction : instructions) {
            try {
                PreparedStatement pStatement = connection.prepareStatement(
                        "INSERT INTO recipe_instructions(recipe_name, step_number, description) VALUES (?,?,?)"
                );
                pStatement.setString(1, recipeName);
                pStatement.setInt(2, instruction.getStepNumber());
                pStatement.setString(3, instruction.getDescription());
                pStatement.execute();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return true;
    }

    public Recipe read(String recipeName) {
        List<Ingredient> ingredients = new ArrayList<>();
        List<Instruction> instructions = new ArrayList<>();
        RecipeType recipeType = RecipeType.VEGAN;
        try {
            PreparedStatement pStatement = connection.prepareStatement(
                    "?"
            );
            pStatement.setString(1, recipeName);
            pStatement.execute();

            // you will have to iterate through a result-set,
            // and fill the lists, as well as get the recipe type

        } catch (SQLException e) {
            System.err.println(e);
        }
        return new Recipe(recipeName, recipeType, ingredients, instructions);
    }

    public boolean update() {
        return false;
    }

    public boolean delete() {
        return false;
    }


}

/*SELECT * FROM `recipe`
right join recipe_instructions
on recipe.recipe_name = recipe_instructions.recipe_name*/
