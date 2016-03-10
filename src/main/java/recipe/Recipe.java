package recipe;

import java.util.List;

/**
 * Created by Nicole on 09.03.2016.
 * Recipe model
 */
public class Recipe {

    public String recipeName;
    public List<String> ingredients;
    public List<String> instructions;

    public Recipe(String recipeName, List<String> ingredients, List<String> instructions) {
        this.recipeName = recipeName;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }

    public String toString() {
        return recipeName + ":\n" + ingredients + "\n" + instructions;
    }


}
