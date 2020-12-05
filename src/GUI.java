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

public class GUI extends Application implements EventHandler<ActionEvent> {
    Stage window;
    Scene loginScene, HomeScene;
    Button RegisterProp, ViewProp, Logout, btLogin, btCreate;
    GridPane createPane, loginPane, pane;
    static PasswordField passInput;
    static TextField nameInput;
    static File source = new File("src/lib/users/userlogin.csv");

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Home");
        pane = new GridPane();
        loginPane = new GridPane();
        createPane = new GridPane();
        pane.setAlignment(Pos.CENTER);

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
        btLogin = new Button("Login");
        btLogin.setTranslateX(150);
        btLogin.setTranslateY(loginScene.getHeight() - 250);
        LoginHandler login = new LoginHandler();
        btLogin.setOnAction(login);

        // Create new account
        btCreate = new Button("Create a new account");
        btCreate.setTranslateX(-65);
        btCreate.setTranslateY(loginScene.getHeight() - 250);
        btCreate.setOnAction(e -> window.setScene(HomeScene));

        // This is the start of home scene
        // Setting Scene 2
        HomeScene = new Scene(pane, 320, 420);

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
        Logout.setTranslateX(280 / 2);
        Logout.setTranslateY(-380 / 2);

        loginPane.getChildren().add(loginHeading);
        loginPane.getChildren().add(btLogin);
        loginPane.getChildren().add(btCreate);
        loginPane.getChildren().add(nameLabel);
        loginPane.getChildren().add(nameInput);
        loginPane.getChildren().add(passLabel);
        loginPane.getChildren().add(passInput);
        pane.getChildren().add(Logout);
        pane.getChildren().add(RegisterProp);
        pane.getChildren().add(ViewProp);
        pane.getChildren().add(select);
        window.setScene(loginScene);
        window.show();
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == RegisterProp) {
            System.out.println("Hallo");
        } else if (event.getSource() == btLogin) {
            try {
                login();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    private void login() throws IOException {
        String combined = LoginGUI.nameInput.getText() + "," + LoginGUI.passInput.getText();

        if (searchForString(combined) && !(combined.equals("username,password"))) {
            System.out.println("\nLogin Successful");
        } else {
            System.out.println("\nLogin Failed. Invalid Username or Password");
        }
    }

    private static boolean searchForString(String s) throws FileNotFoundException {
        final Scanner scanner = new Scanner(LoginGUI.source);

        while (scanner.hasNextLine()) {
            final String lineFromFile = scanner.nextLine();
            if (lineFromFile.contains(s)) {
                scanner.close();
                return true;
            }
        }

        scanner.close();
        return false;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
