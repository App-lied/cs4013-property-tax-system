package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class loginController {


    @FXML
    private Button createNewAccount;

    public void CreateNewAccountOnAction(ActionEvent event){
    Stage stage = (Stage) createNewAccount.getScene().getWindow();
    stage.close();
    }
}
