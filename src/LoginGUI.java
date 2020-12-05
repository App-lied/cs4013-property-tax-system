import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Pos;

public class LoginGUI extends Application {
    @Override    
    public void start(Stage primaryStage){        
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        Scene scene = new Scene(pane, 320, 420);    

        //Login heading
        Text loginHeading = new Text("Login");
        loginHeading.setTranslateX(50);
        loginHeading.setTranslateY(-180);
        loginHeading.setScaleX(2);
        loginHeading.setScaleY(2);

        //Name label
        Label nameLabel = new Label("Username:");
        nameLabel.setTranslateX(-scene.getWidth() / 2 + 100);

        //Name text field
        TextField nameInput = new TextField();
        nameInput.setTranslateX(50);

        //Password label
        Label passLabel = new Label("Password:");
        passLabel.setTranslateX(-scene.getWidth() / 2 + 100);
        passLabel.setTranslateY(30);
        

        //Name password field
        PasswordField passInput = new PasswordField();
        passInput.setTranslateX(50);
        passInput.setTranslateY(30);

        //Enter button
        Button btLogin = new Button("Login");
        btLogin.setTranslateX(150);        
        btLogin.setTranslateY(scene.getHeight() - 250);
        LoginHandler login = new LoginHandler();
        btLogin.setOnAction(login);

        //Create new account
        Button btCreate = new Button("Create a new account");
        btCreate.setTranslateX(-65);        
        btCreate.setTranslateY(scene.getHeight() - 250);
        
        
        pane.getChildren().add(loginHeading);
        pane.getChildren().add(btLogin);
        pane.getChildren().add(btCreate);
        pane.getChildren().add(nameLabel);
        pane.getChildren().add(nameInput);
        pane.getChildren().add(passLabel);
        pane.getChildren().add(passInput);               
        primaryStage.setScene(scene);
        primaryStage.show();        
    }

    
    public static void main(String[] args) {
        launch(args);
        
    }
}

class LoginHandler implements EventHandler<ActionEvent>{
    @Override
    public void handle(ActionEvent e){
        System.out.println("You've logged in");
    }
}
