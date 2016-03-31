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
                "Better Banana Pancakes",
                ImmutableList.of(
                        new Ingredient("Banana", "1 cup"),
                        new Ingredient("Pancake", "100 grams"),
                        new Ingredient("Milk", "1 meter")
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
            connection = DriverManager.getConnection (DATABASE_URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            connection = null;
            e.printStackTrace();
        }
        return connection;
    }
}
