package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Global;
import sample.Munny;
import sample.Users;
import sample.model.UserData;


import java.io.IOException;


public class SignUpController {

    @FXML
    private Label isConnected;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword;

    @FXML
    void login(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/views/loginpage.fxml"));
        Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
//        stage.show();
    }


    @FXML
    void signup(MouseEvent event){
        String username = txtUsername.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        Users users = new Users();
        UserData.getInstance().open();
        if (!UserData.getInstance().getUserLogin(email).isEmpty()){
            isConnected.setText("You are registered. Please login...");
        }else if(UserData.getInstance().getUserLogin(email).isEmpty()){
            UserData.getInstance().setInsertUser(username,email, password,users.getMunny());
            isConnected.setText("Is registered. Please login the account");
        }
        UserData.getInstance().close();
    }
}
