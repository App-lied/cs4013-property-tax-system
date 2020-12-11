import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.HPos;
import javafx.geometry.Pos;

public class GUI extends Application implements EventHandler<ActionEvent> {
    Stage window;
    Scene loginScene, HomeScene, CreateScene, RegisterScene, ConfirmScene, ViewPropScene, propScene, AdminPannelScene,
            adminPropScene;
    Button RegisterProp, ViewProp, Logout, btLogin, btCreate, Confirm, BackMain, CreateNew, BackToLogin,
            BackFromViewProp, BackFromRegister, BackFromProp, AdminLogout, ViewAllProp, PropertyStats, GetPropTaxOwner,
            OverduePropTax;
    GridPane createPane, loginPane, homePane, registerPane, confirmPane, viewpropRoot, propRoot, AdminPannelPane;
    ScrollPane viewpropPane, propPane, AdminpropScroll;
    Group viewpropGroup, paymentsGroup, AdminpropGroup;

    Text createError, loginError, details;
    private PasswordField passInput, newpassInput;
    private TextField nameInput, OwnerIn, AddressIn, PostcodeIn, MarketValIn, NewUsername;
    private CheckBox PrincipalResIn;
    private ComboBox<String> LocationCatIn;
    private static String[] locations = { "Countryside", "Village", "Small Town", "Large Town", "City" };
    static File source = new File("src/lib/users/userlogin.csv");
    User user;
    private ArrayList<Text> addresses = new ArrayList<Text>();
    private ArrayList<Text> payments = new ArrayList<Text>();
    private ArrayList<Button> buttons = new ArrayList<Button>();
    private ArrayList<Property> routeKeyArray = new ArrayList<Property>();
    private ArrayList<Button> pay = new ArrayList<Button>();
    private Property viewedProperty;

    @Override
    public void start(Stage primaryStage) {
        // setting the stage
        window = primaryStage;
        window.setTitle("Home");

        // Setting all the panes
        homePane = new GridPane();
        loginPane = new GridPane();
        createPane = new GridPane();
        registerPane = new GridPane();
        confirmPane = new GridPane();
        viewpropRoot = new GridPane();
        viewpropPane = new ScrollPane();
        viewpropPane.setPrefSize(400, 300);
        viewpropGroup = new Group();
        propRoot = new GridPane();
        AdminPannelPane = new GridPane();
        viewpropPane.setPrefSize(400, 300);
        propRoot = new GridPane();
        propPane = new ScrollPane();
        propPane.setPrefSize(200, 300);

        viewpropGroup = new Group();
        paymentsGroup = new Group();

        // Allign
        loginPane.setAlignment(Pos.CENTER);
        homePane.setAlignment(Pos.CENTER);
        createPane.setAlignment(Pos.CENTER);
        registerPane.setAlignment(Pos.CENTER);
        confirmPane.setAlignment(Pos.CENTER);
        viewpropRoot.setAlignment(Pos.CENTER);
        propRoot.setAlignment(Pos.CENTER);
        AdminPannelPane.setAlignment(Pos.CENTER);

        CreateScene = new Scene(createPane, 420, 500);
        loginScene = new Scene(loginPane, 420, 500);
        HomeScene = new Scene(homePane, 420, 500);
        RegisterScene = new Scene(registerPane, 420, 500);
        ConfirmScene = new Scene(confirmPane, 420, 500);
        ViewPropScene = new Scene(viewpropRoot, 420, 500);
        propScene = new Scene(propRoot, 420, 500);
        AdminPannelScene = new Scene(AdminPannelPane, 420, 500);

        // Login heading
        Text loginHeading = new Text("Login");
        loginHeading.setTranslateX(70);
        loginHeading.setTranslateY(-180);
        loginHeading.setScaleX(2);
        loginHeading.setScaleY(2);

        // Name label
        Label nameLabel = new Label("Username:");
        nameLabel.setTranslateX(-loginScene.getWidth() / 2 + 130);

        // Name text field
        nameInput = new TextField();
        nameInput.setTranslateX(50);

        // Password label
        Label passLabel = new Label("Password:");
        passLabel.setTranslateX(-loginScene.getWidth() / 2 + 130);
        passLabel.setTranslateY(30);

        // Name password field
        passInput = new PasswordField();
        passInput.setTranslateX(50);
        passInput.setTranslateY(30);

        // Enter button
        btLogin = new Button("Login");
        btLogin.setTranslateX(190);
        btLogin.setTranslateY(loginScene.getHeight() - 350);
        btLogin.setOnAction(this);

        // Red error message
        loginError = new Text("Please enter a username and password");
        loginError.setVisible(false);
        loginError.setFill(Color.RED);
        loginError.setTranslateX(0);
        loginError.setTranslateY(100);

        // Create new account
        btCreate = new Button("Create a new account");
        btCreate.setTranslateX(-45);
        btCreate.setTranslateY(loginScene.getHeight() - 350);
        btCreate.setOnAction(this);

        // This is the start of home scene
        // Setting Scene 2

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
        ViewProp.setOnAction(this);

        // Logout Button to go back to the first scene
        Logout = new Button();
        Logout.setText("Logout");
        Logout.setTranslateX(280 / 2);
        Logout.setTranslateY(-380 / 2);
        Logout.setOnAction(this);

        // View properties:
        Text Properties = new Text("Properties");
        Properties.setTranslateX(RegisterScene.getWidth() / 2 - 50);
        Properties.setTranslateY(-225);
        Properties.setScaleX(2);
        Properties.setScaleY(2);

        BackFromViewProp = new Button("Back");
        BackFromViewProp.setTranslateX(0);
        BackFromViewProp.setTranslateY(-200);
        BackFromViewProp.setOnAction(this);

        // Property:
        BackFromProp = new Button("Back");
        BackFromProp.setTranslateX(-90);
        BackFromProp.setTranslateY(-200);
        BackFromProp.setOnAction(this);

        Text singleProperty = new Text("Property:");
        singleProperty.setTranslateX(loginScene.getWidth() / 2 - 150);
        singleProperty.setTranslateY(-225);
        singleProperty.setScaleX(2);
        singleProperty.setScaleY(2);

        // Label of Register Owner
        Text Register = new Text("Register a property");
        Register.setTranslateX(RegisterScene.getWidth() / 2 - 200);
        Register.setTranslateY(-125);
        Register.setScaleX(2);
        Register.setScaleY(2);

        Label Owner = new Label("Owner:");
        Owner.setTranslateX(-RegisterScene.getWidth() / 2 + 100);
        Owner.setTranslateY(-30);

        Label Address = new Label("Address:");
        Address.setTranslateX(-RegisterScene.getWidth() / 2 + 100);
        Address.setTranslateY(0);

        Label Postcode = new Label("Postcode:");
        Postcode.setTranslateX(-RegisterScene.getWidth() / 2 + 100);
        Postcode.setTranslateY(30);

        Label Value = new Label("Market Value:");
        Value.setTranslateX(-RegisterScene.getWidth() / 2 + 100);
        Value.setTranslateY(60);

        Label LocationCat = new Label("Location Catagory:");
        LocationCat.setTranslateX(-RegisterScene.getWidth() / 2 + 100);
        LocationCat.setTranslateY(90);

        Label PrincipalRes = new Label("Principal Residence:");
        PrincipalRes.setTranslateX(-RegisterScene.getWidth() / 2 + 100);
        PrincipalRes.setTranslateY(120);

        OwnerIn = new TextField();
        OwnerIn.setTranslateX(80);
        OwnerIn.setTranslateY(-30);

        AddressIn = new TextField();
        AddressIn.setTranslateX(80);
        AddressIn.setTranslateY(0);

        PostcodeIn = new TextField();
        PostcodeIn.setTranslateX(80);
        PostcodeIn.setTranslateY(30);

        MarketValIn = new TextField();
        MarketValIn.setTranslateX(80);
        MarketValIn.setTranslateY(60);

        LocationCatIn = new ComboBox<String>(FXCollections.observableArrayList(locations));
        LocationCatIn.setTranslateX(80);
        LocationCatIn.setTranslateY(90);

        PrincipalResIn = new CheckBox();
        PrincipalResIn.setTranslateX(80);
        PrincipalResIn.setTranslateY(120);

        Confirm = new Button();
        Confirm.setText("Confirm");
        Confirm.setTranslateX(50);
        Confirm.setTranslateY(160);
        Confirm.setOnAction(this);

        BackFromRegister = new Button("Back");
        BackFromRegister.setTranslateX(-90);
        BackFromRegister.setTranslateY(-200);
        BackFromRegister.setOnAction(this);

        // Confirm Scene
        BackMain = new Button();
        BackMain.setText("Go Back To Main Menu");
        BackMain.setTranslateX(0);
        BackMain.setTranslateY(130);
        BackMain.setOnAction(this);

        Text Registered = new Text("Your property was registered!");
        Registered.setTranslateX(0);
        Registered.setTranslateY(-150);
        Registered.setScaleX(2);
        Registered.setScaleY(2);

        // Create New Account
        // The users new username
        Label NewUsernameLabel = new Label("Username:");
        NewUsernameLabel.setTranslateX(-RegisterScene.getWidth() / 2 + 130);

        NewUsername = new TextField();
        NewUsername.setTranslateX(50);

        // The users new password
        Label newpassLabel = new Label("Password:");
        newpassLabel.setTranslateX(-loginScene.getWidth() / 2 + 130);
        newpassLabel.setTranslateY(30);

        newpassInput = new PasswordField();
        newpassInput.setTranslateX(50);
        newpassInput.setTranslateY(30);

        CreateNew = new Button();
        CreateNew.setText("Create a new Account");
        CreateNew.setTranslateX(120);
        CreateNew.setTranslateY(CreateScene.getHeight() - 350);
        CreateNew.setOnAction(this);

        BackToLogin = new Button("Back");
        BackToLogin.setTranslateX(-45);
        BackToLogin.setTranslateY(CreateScene.getHeight() - 350);
        BackToLogin.setOnAction(this);

        Text CreateNewText = new Text("Create a new Account");
        CreateNewText.setTranslateX(25);
        CreateNewText.setTranslateY(-150);
        CreateNewText.setScaleX(2);
        CreateNewText.setScaleY(2);

        Text enterFields = new Text("Enter the fields below");
        enterFields.setTranslateX(20);
        enterFields.setTranslateY(-100);
        enterFields.setScaleX(1.5);
        enterFields.setScaleY(1.5);

        createError = new Text("Please enter a username and password");
        createError.setVisible(false);
        createError.setFill(Color.RED);
        createError.setTranslateX(0);
        createError.setTranslateY(100);

        // This is the Admin pannel
        Text AdminPannelText = new Text("Admin Pannel");
        GridPane.setHalignment(AdminPannelText, HPos.CENTER);
        AdminPannelText.setTranslateY(-180);
        AdminPannelText.setScaleX(2);
        AdminPannelText.setScaleY(2);

        AdminLogout = new Button();
        AdminLogout.setText("Logout");
        AdminLogout.setTranslateX(300 / 2);
        AdminLogout.setTranslateY(-400 / 2);
        AdminLogout.setOnAction(this);

        // Property buttons for admin
        ViewAllProp = new Button();
        ViewAllProp.setText("Property tax on a single Property");
        ViewAllProp.setTranslateY(-100);
        GridPane.setHalignment(ViewAllProp, HPos.CENTER);
        ViewAllProp.setScaleX(1.25);
        ViewAllProp.setScaleY(1.25);
        ViewAllProp.setOnAction(this);

        GetPropTaxOwner = new Button();
        GetPropTaxOwner.setText("Property tax on a Owner");
        GetPropTaxOwner.setTranslateY(-60);
        GridPane.setHalignment(GetPropTaxOwner, HPos.CENTER);
        GetPropTaxOwner.setScaleX(1.25);
        GetPropTaxOwner.setScaleY(1.25);
        GetPropTaxOwner.setOnAction(this);

        OverduePropTax = new Button();
        OverduePropTax.setText("Overdue Property Tax");
        OverduePropTax.setTranslateY(-20);
        GridPane.setHalignment(OverduePropTax, HPos.CENTER);
        OverduePropTax.setScaleX(1.25);
        OverduePropTax.setScaleY(1.25);
        OverduePropTax.setOnAction(this);

        PropertyStats = new Button();
        PropertyStats.setText("Property Statistics");
        PropertyStats.setTranslateY(20);
        GridPane.setHalignment(PropertyStats, HPos.CENTER);
        PropertyStats.setScaleX(1.25);
        PropertyStats.setScaleY(1.25);
        PropertyStats.setOnAction(this);

        AdminPannelPane.getChildren().add(AdminPannelText);
        AdminPannelPane.getChildren().add(AdminLogout);
        AdminPannelPane.getChildren().add(ViewAllProp);
        AdminPannelPane.getChildren().add(GetPropTaxOwner);
        AdminPannelPane.getChildren().add(OverduePropTax);
        AdminPannelPane.getChildren().add(PropertyStats);

        viewpropRoot.getChildren().add(Properties);
        viewpropRoot.getChildren().add(BackFromViewProp);
        viewpropRoot.getChildren().add(viewpropPane);

        propRoot.getChildren().add(BackFromProp);
        propRoot.getChildren().add(singleProperty);
        propRoot.getChildren().add(propPane);

        createPane.getChildren().add(NewUsernameLabel);
        createPane.getChildren().add(NewUsername);
        createPane.getChildren().add(newpassLabel);
        createPane.getChildren().add(newpassInput);
        createPane.getChildren().add(CreateNew);
        createPane.getChildren().add(CreateNewText);
        createPane.getChildren().add(enterFields);
        createPane.getChildren().add(createError);
        createPane.getChildren().add(BackToLogin);

        confirmPane.getChildren().add(BackMain);
        confirmPane.getChildren().add(Registered);

        registerPane.getChildren().add(Owner);
        registerPane.getChildren().add(Address);
        registerPane.getChildren().add(Postcode);
        registerPane.getChildren().add(Value);
        registerPane.getChildren().add(LocationCat);
        registerPane.getChildren().add(PrincipalRes);
        registerPane.getChildren().add(Confirm);
        registerPane.getChildren().add(Register);
        registerPane.getChildren().add(OwnerIn);
        registerPane.getChildren().add(AddressIn);
        registerPane.getChildren().add(PostcodeIn);
        registerPane.getChildren().add(LocationCatIn);
        registerPane.getChildren().add(MarketValIn);
        registerPane.getChildren().add(PrincipalResIn);
        registerPane.getChildren().add(BackFromRegister);

        loginPane.getChildren().add(loginHeading);
        loginPane.getChildren().add(btLogin);
        loginPane.getChildren().add(btCreate);
        loginPane.getChildren().add(nameLabel);
        loginPane.getChildren().add(nameInput);
        loginPane.getChildren().add(passLabel);
        loginPane.getChildren().add(passInput);
        loginPane.getChildren().add(loginError);

        homePane.getChildren().add(Logout);
        homePane.getChildren().add(RegisterProp);
        homePane.getChildren().add(ViewProp);
        homePane.getChildren().add(select);
        window.setScene(loginScene);
        window.show();
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == Logout || event.getSource() == AdminLogout) {
            window.setScene(loginScene);
            nameInput.clear();
            passInput.clear();
        }
        if (event.getSource() == RegisterProp) {
            window.setScene(RegisterScene);
        }
        if (event.getSource() == Confirm) {
            String address = AddressIn.getText();
            String[] addressSplit = address.split(",");
            address = "";
            int i;
            for (i = 0; i < addressSplit.length; i++) {
                address += addressSplit[i];
            }
            if (!(OwnerIn.getText().equals("") || AddressIn.getText().equals("") || PostcodeIn.getText().equals("")
                    || MarketValIn.getText().equals("") || !Utils.containsOnlyNumbers(MarketValIn.getText()))) {

                if (user instanceof PropertyOwner) {
                    ((PropertyOwner) user).getPropertyList()
                            .add(new Property(OwnerIn.getText(), address, PostcodeIn.getText(),
                                    Double.parseDouble(MarketValIn.getText()), categoryAsInt(LocationCatIn.getValue()),
                                    PrincipalResIn.isSelected()));
                    ((PropertyOwner) user).getPropertyList().get(((PropertyOwner) user).getPropertyList().size() - 1)
                            .writeToFile(user.getUsername());
                }

                OwnerIn.clear();
                AddressIn.clear();
                PostcodeIn.clear();
                MarketValIn.clear();
                LocationCatIn.valueProperty().set(null);

                window.setScene(ConfirmScene);
            }
        }
        if (event.getSource() == BackMain) {
            window.setScene(HomeScene);
        }
        if (event.getSource() == ViewProp) {
            int i;
            int j = -130;

            for (i = 0; i < ((PropertyOwner) user).getPropertyList().size(); i++) {
                final int l = i;
                addresses.add(new Text(((PropertyOwner) user).getPropertyList().get(i).getAddress()));
                addresses.get(i).setTranslateX(-80);
                addresses.get(i).setTranslateY(j + 15);
                buttons.add(new Button("View"));
                buttons.get(i).setTranslateX(250);
                buttons.get(i).setTranslateY(j);
                buttons.get(i).setOnAction(e -> {
                    viewedProperty = ((PropertyOwner) user).getPropertyList().get(l);
                    details = new Text(viewedProperty.toString());
                    details.setTranslateX(loginScene.getWidth() / 2 - 250);
                    details.setTranslateY(-75);
                    details.setScaleX(1.25);
                    details.setScaleY(1.25);
                    propRoot.getChildren().add(details);
                    System.out.println(viewedProperty);
                    window.setScene(propScene);
                    int k;
                    for (k = 0; k < payments.size(); k++) {
                        payments.add(new Text(
                                ((PropertyOwner) user).getPropertyList().get(l).getPaymentList().get(l).toString()));
                        payments.get(k).setTranslateX(250);
                        payments.get(k).setTranslateY(-130);
                        paymentsGroup.getChildren().add(payments.get(k));

                    }
                    propPane.setContent(paymentsGroup);
                });
                j += 30;
                viewpropGroup.getChildren().add(addresses.get(i));
                viewpropGroup.getChildren().add(buttons.get(i));
            }
            viewpropPane.setContent(viewpropGroup);
            window.setScene(ViewPropScene);
        }

        if (event.getSource() == ViewAllProp) {
            // for( int i = 0; i< ((Property))

            // String routeKey = ((Property) routeKeyArray).getPostcode().substring(0, 3);

        }

        if (event.getSource() == btCreate) {
            window.setScene(CreateScene);
            loginError.setVisible(false);
        }
        if (event.getSource() == CreateNew) {
            if (!NewUsername.getText().equals("") && !newpassInput.getText().equals("")) {
                try {
                    FileWriter writer = new FileWriter("src/lib/users/userlogin.csv", true);
                    BufferedWriter bw = new BufferedWriter(writer);
                    PrintWriter pw = new PrintWriter(bw);
                    pw.print("\n" + NewUsername.getText() + "," + newpassInput.getText());
                    pw.close();
                } catch (IOException e) {
                    System.out.println("An error has occurred");
                    e.printStackTrace();
                }
                createError.setVisible(false);
                window.setScene(loginScene);
            } else {
                createError.setVisible(true);
            }

        }
        if (event.getSource() == btLogin) {
            try {
                loginError.setVisible(false);
                login();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        if (event.getSource() == BackToLogin) {
            window.setScene(loginScene);
            createError.setVisible(false);
        }
        if (event.getSource() == BackFromViewProp) {
            window.setScene(HomeScene);
            viewpropGroup.getChildren().clear();
            addresses.clear();

        }

        if (event.getSource() == BackFromProp) {
            window.setScene(ViewPropScene);
        }

        if (event.getSource() == BackFromRegister) {
            window.setScene(HomeScene);
        }
    }

    private void login() throws IOException {
        String combined = nameInput.getText() + "," + passInput.getText() + ",";

        if (Utils.searchForString(source, combined)
                && !(combined.equals("username,password,") || combined.equals(","))) {
            loginError.setVisible(false);

            if (Utils.searchForString(source, combined + "true")) {
                user = new Admin(nameInput.getText(), passInput.getText());
                window.setScene(AdminPannelScene);
            } else {
                user = new PropertyOwner(nameInput.getText(), passInput.getText());
                window.setScene(HomeScene);
            }

        } else {
            loginError.setVisible(true);
            System.out.println("\nLogin Failed. Invalid Username or Password");
        }
    }

    private static int categoryAsInt(String category) {
        int i;
        for (i = 0; i < locations.length; i++) {
            if (category.equals(locations[i])) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        launch(args);

    }
}
