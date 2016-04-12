package recipe;

import java.util.List;

/**
 * Created by Nicole on 09.03.2016.
 * Recipe model
 */
public class Recipe {

    private String recipeName;
    private RecipeType recipeType;
    private List<Ingredient> ingredients;
    private List<Instruction> instructions;

    public Recipe(String recipeName, RecipeType recipeType, List<Ingredient> ingredients, List<Instruction> instructions) {
        this.recipeName = recipeName;
        this.recipeType = recipeType;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    public String getRecipeName() { return recipeName; }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public RecipeType getRecipeType() {
        return recipeType;
    }

    public void setRecipeType(RecipeType recipeType) {
        this.recipeType = recipeType;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    public String toString() {
        return recipeName + " (" + recipeType.name() + "):\n" + ingredients + "\n" + instructions;
    }



}
