import com.google.common.collect.ImmutableList;
import recipe.Ingredient;
import recipe.RecipeDAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {

    public static void main(String[] args) throws Exception {
        Connection connection = getConnection(); // This could have been new DbConnector();
        RecipeDAO recipeDAO = new RecipeDAO(connection);
        recipeDAO.create(
                "Curry Supreme",
                ImmutableList.of(
                        new Ingredient("Chicken thighs", "600 grams"),
                        new Ingredient("Coconut milk", "1 can"),
                        new Ingredient("Curry paste", "1 tablespoon"),
                        new Ingredient("Carrot", "2 pieces"),
                        new Ingredient("Potato", "2 pieces")
                )
        );
    }

    private static Connection getConnection() throws ClassNotFoundException {
        Connection connection;
        String DATABASE_URL = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/nauybeng";
        String USERNAME = "nauybeng";
        String PASSWORD = "wjbxoEpx";
        Class.forName("com.mysql.jdbc.Driver");
        try {
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            connection = null;
            e.printStackTrace();
        }
        return connection;
    }
}
