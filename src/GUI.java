import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

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
import jdk.jshell.execution.Util;
import javafx.geometry.HPos;
import javafx.geometry.Pos;

public class GUI extends Application implements EventHandler<ActionEvent> {
    Stage window;
    Scene loginScene, HomeScene, CreateScene, RegisterScene, ConfirmScene, ViewPropScene, propScene, AdminPannelScene,
            AdminUsersScene, AdminOverPropScene, AdminStatsScene, AdminInvestigateChangeScene, AdminPropScene, AdminViewPropScene, adminOverdueScene;
    Button RegisterProp, ViewProp, Logout, btLogin, btCreate, Confirm, BackMain, CreateNew, BackToLogin,
            BackFromViewProp, BackFromRegister, BackFromProp, AdminLogout, ViewUsers, PropertyStats, GetPropTaxOwner,
            OverduePropTax, Search, getStats, BackFromStats, investigateChanges, calculateChange, backFromChanges;
    GridPane createPane, loginPane, homePane, registerPane, confirmPane, viewpropRoot, propRoot, AdminPannelPane,
            AdminOverPropPane, AdminStatsPane, AdminInvestigateChangePane;
    ScrollPane viewpropPane, propPane, AdminUsersPane;
    Group viewpropGroup, paymentsGroup, AdminUsersGroup;       
    
    Text createError, loginError, details, statistics, routingKeyError, change;
    Label routingKey, bracket1, bracket2, bracket3;
    private PasswordField passInput, newpassInput;
    private TextField nameInput, OwnerIn, AddressIn, PostcodeIn, MarketValIn, NewUsername, year, areaCodeText, routingKeyText, bracket1Text,
    bracket2Text, bracket3Text;
    private CheckBox PrincipalResIn;
    private ComboBox<String> LocationCatIn;
    private static String[] locations = { "Countryside", "Village", "Small Town", "Large Town", "City" };
    static File source = new File("src/lib/users/userlogin.csv");
    User user;
    private ArrayList<Text> addresses = new ArrayList<Text>();
    private ArrayList<Text> viewAdresses = new ArrayList<Text>();
    private ArrayList<Text> payments = new ArrayList<Text>();
    private ArrayList<Button> buttons = new ArrayList<Button>();
    private ArrayList<Button> pay = new ArrayList<Button>();
    private Property viewedProperty;
    private PropertyOwner viewedUser;
    Admin admin;
    private ArrayList<Text> userlist = new ArrayList<Text>();
    private ArrayList<Button> viewUser = new ArrayList<Button>();
    private ArrayList<Payment> routedPayments;
    Payment p;

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
        AdminUsersPane = new ScrollPane();
        AdminOverPropPane = new GridPane();
        AdminStatsPane = new GridPane();
        AdminInvestigateChangePane = new GridPane();
        viewpropPane = new ScrollPane();
        viewpropPane.setPrefSize(400, 300);
        viewpropGroup = new Group();
        propRoot = new GridPane();
        AdminPannelPane = new GridPane();
        viewpropPane.setPrefSize(400, 300);
        propRoot = new GridPane();
        propPane = new ScrollPane();
        propPane.setPrefSize(400, 100);
        AdminPropPane = new ScrollPane();
        AdminViewPropPane = new ScrollPane();
        adminOverduePane = new ScrollPane();

        viewpropGroup = new Group();
        paymentsGroup = new Group();
        AdminUsersGroup = new Group();
        AdminPropGroup = new Group();
        AdminOverdueGroup = new Group();

        // Allign
        loginPane.setAlignment(Pos.CENTER);
        homePane.setAlignment(Pos.CENTER);
        createPane.setAlignment(Pos.CENTER);
        registerPane.setAlignment(Pos.CENTER);
        confirmPane.setAlignment(Pos.CENTER);
        viewpropRoot.setAlignment(Pos.CENTER);
        propRoot.setAlignment(Pos.CENTER);
        AdminPannelPane.setAlignment(Pos.CENTER);
        AdminOverPropPane.setAlignment(Pos.CENTER);
        AdminStatsPane.setAlignment(Pos.CENTER);
        AdminInvestigateChangePane.setAlignment(Pos.CENTER);

        CreateScene = new Scene(createPane, 420, 500);
        loginScene = new Scene(loginPane, 420, 500);
        HomeScene = new Scene(homePane, 420, 500);
        RegisterScene = new Scene(registerPane, 420, 500);
        ConfirmScene = new Scene(confirmPane, 420, 500);
        ViewPropScene = new Scene(viewpropRoot, 420, 500);
        propScene = new Scene(propRoot, 420, 500);
        AdminPannelScene = new Scene(AdminPannelPane, 420, 500);
        AdminUsersScene = new Scene(AdminUsersPane, 420, 500);
        AdminOverPropScene = new Scene(AdminOverPropPane, 420, 500);
        AdminStatsScene = new Scene(AdminStatsPane, 420, 500);
        AdminInvestigateChangeScene = new Scene(AdminInvestigateChangePane, 420, 500);
        AdminPropScene = new Scene(AdminPropPane, 420, 500);
        AdminViewPropScene = new Scene(AdminViewPropPane, 420, 500);
        adminOverdueScene = new Scene(adminOverduePane, 420, 500);

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
        BackFromProp.setTranslateX(0);
        BackFromProp.setTranslateY(-200);
        BackFromProp.setOnAction(this);

        Text singleProperty = new Text("Property:");
        singleProperty.setTranslateX(loginScene.getWidth() / 2 - 50);
        singleProperty.setTranslateY(-225);
        singleProperty.setScaleX(2);
        singleProperty.setScaleY(2);

        details = new Text();

        propPane.setTranslateY(100);

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
        Text AdminPannelText = new Text("Admin Panel");
        GridPane.setHalignment(AdminPannelText, HPos.CENTER);
        AdminPannelText.setTranslateY(-180);
        AdminPannelText.setScaleX(2);
        AdminPannelText.setScaleY(2);

        AdminLogout = new Button();
        AdminLogout.setText("Logout");
        AdminLogout.setTranslateX(200);
        AdminLogout.setTranslateY(-220);
        AdminLogout.setOnAction(this);

        // Property buttons for admin
        ViewUsers = new Button();
        ViewUsers.setText("View Users");
        ViewUsers.setTranslateY(-100);
        GridPane.setHalignment(ViewUsers, HPos.CENTER);
        ViewUsers.setScaleX(1.25);
        ViewUsers.setScaleY(1.25);
        ViewUsers.setOnAction(this);

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

        investigateChanges = new Button();
        investigateChanges.setText("Investigate Changes to Tax Rates");
        investigateChanges.setTranslateY(20);
        GridPane.setHalignment(investigateChanges, HPos.CENTER);
        investigateChanges.setScaleX(1.25);
        investigateChanges.setScaleY(1.25);
        investigateChanges.setOnAction(this);

        PropertyStats = new Button();
        PropertyStats.setText("Property Statistics");
        PropertyStats.setTranslateY(60);
        GridPane.setHalignment(PropertyStats, HPos.CENTER);
        PropertyStats.setScaleX(1.25);
        PropertyStats.setScaleY(1.25);
        PropertyStats.setOnAction(this);                

        // Admin Overdue prop tax

        Text EnterDetails = new Text("Enter details below");
        GridPane.setHalignment(EnterDetails, HPos.CENTER);
        EnterDetails.setTranslateY(-125);
        EnterDetails.setScaleX(2);
        EnterDetails.setScaleY(2);

        Label EnterYear = new Label("Enter year:");
        EnterYear.setTranslateX(10);

        year = new TextField();
        year.setTranslateX(70);

        Label areaCode = new Label("(Optional) Search by area code:");
        areaCode.setTranslateX(-100);
        areaCode.setTranslateY(30);

        areaCodeText = new TextField();
        areaCodeText.setTranslateX(70);
        areaCodeText.setTranslateY(30);

        Search = new Button();
        Search.setText("Search");
        Search.setTranslateX(50);
        Search.setTranslateY(140);
        Search.setOnAction(this);

        bracket1 = new Label("Tax rate in % for values 150,000 - 100,000: ");
        bracket1.setTranslateX(-90);
        bracket1.setTranslateY(-100);

        bracket1Text = new TextField();
        bracket1Text.setTranslateX(140);
        bracket1Text.setTranslateY(-100);
        bracket1Text.setPrefWidth(50);

        bracket2 = new Label("Tax rate in % for values 400,000 - 650,000: ");
        bracket2.setTranslateX(-90);
        bracket2.setTranslateY(-70);

        bracket2Text = new TextField();
        bracket2Text.setTranslateX(140);
        bracket2Text.setTranslateY(-70);        
        bracket2Text.setPrefWidth(50);

        bracket3 = new Label("Tax rate in % for values 650,000 and over: ");
        bracket3.setTranslateX(-90);
        bracket3.setTranslateY(-40);

        bracket3Text = new TextField();
        bracket3Text.setTranslateX(140);
        bracket3Text.setTranslateY(-40);        
        bracket3Text.setPrefWidth(50);

        calculateChange = new Button("Calculate Change");
        GridPane.setHalignment(calculateChange, HPos.CENTER);
        calculateChange.setOnAction(this);

        change = new Text();        
        change.setTranslateY(100);

        backFromChanges = new Button("Back");
        backFromChanges.setTranslateX(-50);
        backFromChanges.setTranslateY(-200);
        backFromChanges.setOnAction(this);


        // Admin Statistics

        routingKey = new Label("Enter the 3 character eircode \n routing key to search by:");
        routingKey.setTranslateX(-100);
        routingKey.setTranslateY(-100);

        routingKeyText = new TextField();
        routingKeyText.setTranslateX(100);
        routingKeyText.setTranslateY(-100);

        getStats = new Button("Get Statistics");
        GridPane.setHalignment(getStats, HPos.CENTER);
        getStats.setOnAction(this);

        statistics = new Text();
        statistics.setScaleX(1.15);
        statistics.setScaleY(1.15);
        statistics.setTranslateX(-70);
        statistics.setTranslateY(100);

        BackFromStats = new Button("Back");
        BackFromStats.setTranslateX(-50);
        BackFromStats.setTranslateY(-200);
        BackFromStats.setOnAction(this);

        routingKeyError = new Text("Invalid routing key");
        routingKeyError.setTranslateX(100);
        routingKeyError.setTranslateY(-80);
        routingKeyError.setFill(Color.RED);
        routingKeyError.setVisible(false);

        AdminInvestigateChangePane.getChildren().add(bracket1);
        AdminInvestigateChangePane.getChildren().add(bracket2);
        AdminInvestigateChangePane.getChildren().add(bracket3);
        AdminInvestigateChangePane.getChildren().add(bracket1Text);
        AdminInvestigateChangePane.getChildren().add(bracket2Text);
        AdminInvestigateChangePane.getChildren().add(bracket3Text);
        AdminInvestigateChangePane.getChildren().add(calculateChange);
        AdminInvestigateChangePane.getChildren().add(change);
        AdminInvestigateChangePane.getChildren().add(backFromChanges);

        AdminStatsPane.getChildren().add(routingKey);
        AdminStatsPane.getChildren().add(routingKeyText);
        AdminStatsPane.getChildren().add(getStats);
        AdminStatsPane.getChildren().add(statistics);
        AdminStatsPane.getChildren().add(BackFromStats);
        AdminStatsPane.getChildren().add(routingKeyError);

        backOverdue = new Button("Back");
        backOverdue.setTranslateX(300 / 2);
        backOverdue.setTranslateY(-400 / 2);
        backOverdue.setOnAction(this);

        AdminOverPropPane.getChildren().add(EnterYear);
        AdminOverPropPane.getChildren().add(year);
        AdminOverPropPane.getChildren().add(areaCode);
        AdminOverPropPane.getChildren().add(areaCodeText);
        AdminOverPropPane.getChildren().add(EnterDetails);
        AdminOverPropPane.getChildren().add(Search);
        AdminOverPropPane.getChildren().add(backOverdue);

        AdminPannelPane.getChildren().add(AdminPannelText);
        AdminPannelPane.getChildren().add(AdminLogout);
        AdminPannelPane.getChildren().add(ViewUsers);
        AdminPannelPane.getChildren().add(GetPropTaxOwner);
        AdminPannelPane.getChildren().add(OverduePropTax);
        AdminPannelPane.getChildren().add(investigateChanges);
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
                    details.setText((viewedProperty.toString()));
                    details.setTranslateX(loginScene.getWidth() / 2 - 170);
                    details.setTranslateY(-75);
                    details.setScaleX(1.25);
                    details.setScaleY(1.25);
                    propRoot.getChildren().add(details);
                    System.out.println(viewedProperty);
                    window.setScene(propScene);
                    int k, n = 0;
                    int o = 100;
                    for (k = 0; k < viewedProperty.getPaymentList().size(); k++) {
                        final int m = k;
                        payments.add(
                                new Text(Utils.removeLineBreakers(viewedProperty.getPaymentList().get(k).toString())));
                        payments.get(k).setTranslateX(loginScene.getWidth() / 2 - 50);
                        payments.get(k).setTranslateY(o);
                        if (!viewedProperty.getPaymentList().get(k).isPaid()) {
                            pay.add(new Button("Pay"));
                            pay.get(k).setTranslateX(loginScene.getWidth() / 2 + 280);
                            pay.get(k).setTranslateY(o - 15);
                            pay.get(k).setOnAction(f -> {
                                int y = viewedProperty.getPaymentList().get(m).getYear();
                                double a = viewedProperty.getPaymentList().get(m).getAmount();
                                Payment temp = new Payment(y, a, true);
                                viewedProperty.getPaymentList().get(m).setPaid(true);
                                payments.get(m).setText(
                                        Utils.removeLineBreakers(viewedProperty.getPaymentList().get(m).toString()));
                                paymentsGroup.getChildren().remove(m);
                                try {
                                    viewedProperty.getPaymentList().get(m).removePayment(viewedProperty.getPostcode());
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                                viewedProperty.getPaymentList().remove(viewedProperty.getPaymentList().get(m));
                                temp.writeToFile(viewedProperty.getPostcode());
                                viewedProperty.getPaymentList().add(temp);
                            });
                            paymentsGroup.getChildren().add(pay.get(k));
                        } else {
                            pay.add(null);
                        }
                        paymentsGroup.getChildren().add(payments.get(k));
                        o += 30;
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
        if (event.getSource() == ViewUsers) {
            int i;
            int j = -130;
            for (i = 0; i < ((Admin) user).getUsers().size(); i++) {
                int l = i;
                userlist.add(new Text(((Admin) user).getUsers().get(i).getUsername()));
                userlist.get(i).setTranslateX(-80);
                userlist.get(i).setTranslateY(j + 15);
                viewUser.add(new Button("View"));
                viewUser.get(i).setTranslateX(250);
                viewUser.get(i).setTranslateY(j);
                viewUser.get(i).setOnAction(e -> {

                    viewedUser = ((Admin) user).getUsers().get(l);
                    viewedProperty = ((PropertyOwner) viewedUser).getPropertyList().get(l);
                    for (int a = 0; a < viewedUser.getPropertyList().size(); a++) {
                        viewAdresses.add(new Text(((PropertyOwner) viewedUser).getPropertyList().get(l).getAddress()));
                        System.out.println(viewAdresses);
                        window.setScene(AdminViewPropScene);
                        int k = 0;
                        int o = 100;
                        for (int c = 0; c < viewedUser.getPropertyList().size(); c++) {
                            final int m = k;
                            payments.add(new Text(
                                    Utils.removeLineBreakers(viewedProperty.getPaymentList().get(k).toString())));
                            payments.get(k).setTranslateX(loginScene.getWidth() / 2 - 50);
                            payments.get(k).setTranslateY(o);
                            if (!viewedProperty.getPaymentList().get(k).isPaid()) {
                                pay.add(new Button("Pay"));
                                pay.get(k).setTranslateX(loginScene.getWidth() / 2 + 280);
                                pay.get(k).setTranslateY(o - 15);
                                pay.get(k).setOnAction(f -> {
                                    int y = viewedProperty.getPaymentList().get(m).getYear();
                                    double b = viewedProperty.getPaymentList().get(m).getAmount();
                                    Payment temp = new Payment(y, b, true);
                                    viewedProperty.getPaymentList().get(m).setPaid(true);
                                    payments.get(m).setText(Utils
                                            .removeLineBreakers(viewedProperty.getPaymentList().get(m).toString()));
                                    AdminPropGroup.getChildren().remove(m);
                                    try {
                                        viewedProperty.getPaymentList().get(m)
                                                .removePayment(viewedProperty.getPostcode());
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                    viewedProperty.getPaymentList().remove(viewedProperty.getPaymentList().get(m));
                                    temp.writeToFile(viewedProperty.getPostcode());
                                    viewedProperty.getPaymentList().add(temp);
                                });
                                AdminPropGroup.getChildren().add(pay.get(k));
                            } else {
                                pay.add(null);
                            }
                            AdminPropGroup.getChildren().add(payments.get(k));
                            o += 30;
                        }
                        AdminPropPane.setContent(AdminPropGroup);
                    }
                });
                j += 30;
                AdminPropGroup.getChildren().add(userlist.get(i));
                AdminPropGroup.getChildren().add(viewUser.get(i));
            }
            AdminPropPane.setContent(AdminPropGroup);
            window.setScene(AdminPropScene);
        }

        if (event.getSource() == OverduePropTax) {
            window.setScene(AdminOverPropScene);
        }

        if (event.getSource() == Search) {
            ArrayList<Payment> AdminPayments = new ArrayList<Payment>();
            ArrayList<Text> AdminPaymentsTemp = new ArrayList<Text>();

            if (areaCodeText.getText().length() == 3) {
                AdminPayments = Utils.findPaymentsByRoutingKey(areaCodeText.getText());
            }
            int o = -150;
            for (int i = 0; i < AdminPayments.size(); i++) {

                if (AdminPayments.get(i).getYear() == Integer.parseInt(year.getText())) {
                    AdminPaymentsTemp.add(new Text(AdminPayments.get(i).toString()));
                    o += 30;
                    AdminOverdueGroup.getChildren().add(AdminPaymentsTemp.get(i));
                }
            }
            adminOverduePane.setContent(AdminOverdueGroup);
            window.setScene(adminOverdueScene);
        }

        if(event.getSource() == PropertyStats){
            window.setScene(AdminStatsScene);             
        }

        if(event.getSource() == investigateChanges){
            window.setScene(AdminInvestigateChangeScene);
        }

        if(event.getSource() == getStats){
            if(routingKeyText.getText().length() == 3){
                routedPayments = Utils.findPaymentsByRoutingKey(routingKeyText.getText().toUpperCase());
                double total = 0;
                double count = 0;
                for(Payment p : routedPayments){
                    if(p.isPaid()){
                        total = total + p.getAmount();
                        count++;
                    }
                }
                
                
                for(Payment p : routedPayments){
                    if(p.isPaid()){
                        total = total + p.getAmount();
                        
                    }
                }            
                statistics.setText("Total tax paid for this area: €" + total + "\n" +
                "Average tax paid for this area: €" + String.format("%.2f",(total / count)) + "\n" +
                ((int)count) + "/" + routedPayments.size() + " payments made.\n" + 
                    "" + String.format("%.2f",((double)count / (double)routedPayments.size())*100) + "% payment rate.\n"
                );
                routingKeyError.setVisible(false);
                BackFromStats.setTranslateX(-40);
                routingKey.setTranslateX(-90);
            }
            else{
                routingKeyError.setVisible(true);
            }
        }

        if(event.getSource() == calculateChange){
            if(Utils.containsOnlyNumbers(bracket1Text.getText())){
                double[] rates = {0.0, Double.parseDouble(bracket1Text.getText()), Double.parseDouble(bracket2Text.getText()), Double.parseDouble(bracket3Text.getText())};
                ArrayList<Payment> newPaymentData = new ArrayList<Payment>();
                for(User u : ((Admin)user).getUsers()){
                    for(Property p : ((PropertyOwner)u).getPropertyList()){
                        TaxCalculator calculator = new TaxCalculator(p);
                        calculator.setBrackets(rates);
                        Payment pay = new Payment(2020, 0, false);
                        pay.setAmount(calculator.getTaxPayable(pay));
                        newPaymentData.add(pay);
                    }
                }

                Collections.sort(newPaymentData);
                double total = 0;
                for(Payment p : newPaymentData){
                    total = total + p.getAmount();
                }
                
                change.setText("Total tax to be collected with these rates: €" + String.format("%.2f", total) + "\n" +
                            "Mean tax payment: €" + String.format("%.2f", (total / newPaymentData.size())) + "\n" +
                            "Median tax payment: €" + String.format("%.2f", newPaymentData.get(newPaymentData.size() / 2).getAmount()) + "\n");
                bracket1.setTranslateX(-40);
                bracket2.setTranslateX(-40);
                bracket3.setTranslateX(-40);
                bracket1Text.setTranslateX(190);
                bracket2Text.setTranslateX(190);
                bracket3Text.setTranslateX(190);
                backFromChanges.setTranslateX(0);
            }
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
            propRoot.getChildren().remove(details);
            payments.clear();
            pay.clear();
            paymentsGroup.getChildren().clear();
        }

        if (event.getSource() == BackFromRegister) {
            window.setScene(HomeScene);
        }

        if (event.getSource() == BackFromStats){
            routingKeyText.clear();
            statistics.setText("");
            routingKeyError.setVisible(false);
            BackFromStats.setTranslateX(-20);
            routingKey.setTranslateX(-20);
            window.setScene(AdminPannelScene);
        }

        if (event.getSource() == backFromChanges){
            bracket1Text.clear();
            bracket2Text.clear();
            bracket3Text.clear();
            change.setText("");
            window.setScene(AdminPannelScene);
            bracket1.setTranslateX(-90);
            bracket2.setTranslateX(-90);
            bracket3.setTranslateX(-90);
            bracket1Text.setTranslateX(140);
            bracket2Text.setTranslateX(140);
            bracket3Text.setTranslateX(140);
            backFromChanges.setTranslateX(-50);
        }

        if (event.getSource() == backOverdue) {
            window.setScene(AdminPannelScene);
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