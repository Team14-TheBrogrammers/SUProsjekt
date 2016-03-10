import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class JavaFxExample extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    @Override
    public void start(Stage stage) {
        stage.setTitle("Hello World!");
        Button button = new Button();
        Label label = new Label();
        button.setText("Say 'Hello World'");
        button.setOnAction((event) -> label.setText("Hello world"));
        
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(button, label);
        StackPane.setAlignment(button, Pos.TOP_CENTER);
        StackPane.setAlignment(label, Pos.CENTER);
        stage.setScene(new Scene(stackPane, 300, 150));
        stage.show();
    }
}