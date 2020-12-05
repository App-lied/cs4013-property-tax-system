import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

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

public class LoginGUI extends Application{
    static PasswordField passInput;
    static TextField nameInput;
    static File source = new File("src/lib/users/userlogin.csv");
    Stage window;
    Scene loginScene, createScene;

    @Override
    public void start(Stage primaryStage) {        
        window = primaryStage;
        GridPane loginPane = new GridPane();
        GridPane createPane = new GridPane();
        createScene = new Scene(createPane, 320, 420);
        loginPane.setAlignment(Pos.CENTER);
        loginScene = new Scene(loginPane, 320, 420);

        // Login heading
        Text loginHeading = new Text("Login");
        loginHeading.setTranslateX(50);
        loginHeading.setTranslateY(-180);
        loginHeading.setScaleX(2);
        loginHeading.setScaleY(2);

        // Name label
        Label nameLabel = new Label("Username:");
        nameLabel.setTranslateX(-loginScene.getWidth() / 2 + 100);

        // Name text field
        nameInput = new TextField();
        nameInput.setTranslateX(50);

        // Password label
        Label passLabel = new Label("Password:");
        passLabel.setTranslateX(-loginScene.getWidth() / 2 + 100);
        passLabel.setTranslateY(30);

        // Name password field
        passInput = new PasswordField();
        passInput.setTranslateX(50);
        passInput.setTranslateY(30);

        // Enter button
        Button btLogin = new Button("Login");
        btLogin.setTranslateX(150);
        btLogin.setTranslateY(loginScene.getHeight() - 250);
        LoginHandler login = new LoginHandler();
        btLogin.setOnAction(login);

        // Create new account
        Button btCreate = new Button("Create a new account");
        btCreate.setTranslateX(-65);
        btCreate.setTranslateY(loginScene.getHeight() - 250);
        btCreate.setOnAction(e -> window.setScene(createScene));

        loginPane.getChildren().add(loginHeading);
        loginPane.getChildren().add(btLogin);
        loginPane.getChildren().add(btCreate);
        loginPane.getChildren().add(nameLabel);
        loginPane.getChildren().add(nameInput);
        loginPane.getChildren().add(passLabel);
        loginPane.getChildren().add(passInput);
        window.setScene(loginScene);
        window.show();

    }
    public static void main(String[] args) {
        launch(args);

    }
}

class LoginHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent e) {
        try {
            login();
        } catch (IOException e1) {            
            e1.printStackTrace();
        }        
    }

    private void login() throws IOException {
        String combined = LoginGUI.nameInput.getText() + "," + LoginGUI.passInput.getText() + ",";
        
        if(searchForString(combined) && !(combined.equals("username,password"))){
            System.out.println("\nLogin Successful");            
        }
        else{
            System.out.println("\nLogin Failed. Invalid Username or Password");            
        }
    }

    private static boolean searchForString(String s) throws FileNotFoundException{
        final Scanner scanner = new Scanner(LoginGUI.source);

        while (scanner.hasNextLine()){
            final String lineFromFile = scanner.nextLine();
            if(lineFromFile.contains(s)){
                scanner.close();
                return true;
            }
        }

        scanner.close();
        return false;
    }
}


