package recipe;

/**
 * Created by Nicole on 07.04.2016.
 * Instruction class for recipe_instructions table
 */
@lombok.ToString
public class Instruction {

    private int stepNumber;
    private String description;

    public Instruction(int stepNumber, String description){
        this.stepNumber = stepNumber;
        this.description = description;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
