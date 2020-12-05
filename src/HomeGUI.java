import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class HomeGUI extends Application implements EventHandler<ActionEvent> {

    Stage window;
    Scene scene1, scene2;
    Button RegisterProp, ViewProp, Logout;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Home");
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);

        // Setting Scene 2
        scene2 = new Scene(pane, 320, 420);

        // Select text on the Home Page
        Text select = new Text("Select An Option Below");
        select.setTranslateY(-75);
        select.setScaleX(2);
        select.setScaleY(2);

        // Register property button
        RegisterProp = new Button();
        RegisterProp.setText("Register a Property");
        RegisterProp.setTranslateX(75);
        RegisterProp.setOnAction(this);

        // View Property button
        ViewProp = new Button();
        ViewProp.setText("View a Property");
        ViewProp.setTranslateX(-50);

        // Logout Button to go back to the first scene
        Logout = new Button();
        Logout.setText("Logout");

        pane.getChildren().add(RegisterProp);
        pane.getChildren().add(ViewProp);
        pane.getChildren().add(select);
        window.setScene(scene2);
        window.show();
    }

    @Override
    public void handle(ActionEvent event) {
        // TODO Auto-generated method stub
        if (event.getSource() == RegisterProp) {
            System.out.println("Hallo");
        }
    }
}
