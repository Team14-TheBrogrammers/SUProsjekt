import recipe.RecipeDAO;

public class Main {
    public static void main(String[] args) {
        RecipeDAO recipeDAO = new RecipeDAO();
        System.out.println(recipeDAO.read());
    }
}
