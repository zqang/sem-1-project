package sample.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Cat;
import sample.Global;
import sample.TTL;
import sample.model.CatData;
import sample.model.UserData;
import sample.Users;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;



public class LoginController {
    public static long timeToLive;
    public static Timer timer;
    public static int second;

@FXML
private Label isConnected;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword;

    @FXML
    void login(MouseEvent event) throws SQLException, IOException {
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        UserData.getInstance().open();
        CatData.getInstance().open();
        List<Users> users =  UserData.getInstance().getUserLogin(email);
        if(users.isEmpty()){
            isConnected.setText("no such email");
            Parent root = FXMLLoader.load(getClass().getResource("/sample/views/loginpage.fxml"));
            Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        }

        for(Users user : users){
            if(user.getPassword().equalsIgnoreCase(password)) {
                List<Cat> cats = CatData.getInstance().getCat(user.getUsername());
                if(cats.isEmpty()){
                    Global.setUsername(user.getUsername());
                    Global.setUsers(user);
                    Parent root = FXMLLoader.load(getClass().getResource("/sample/views/choosecharacter.fxml"));
                    Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);

                }else{
                    for(Cat cat: cats){
                        Global.setCat(cat);
//                        Global.cat.setFurColour(cat.getFurColour());
//                        Global.cat.setEyeColour(cat.getEyeColour());
                    }
                    Parent root = FXMLLoader.load(getClass().getResource("/sample/views/home.fxml"));
                    Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    timeToLive = Global.cat.getTimeToLive();
                    TTL ttl = Global.cat.getTtl();
                    second = (int) timeToLive /1000;
                    timer = new Timer();
                    int delay = 1000;
                    int period =1000;
                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            timeToLive-=1;
                            Global.cat.setTimeToLive(timeToLive);
                            ttl.addTtl(timeToLive*1000);
                            System.out.println(timeToLive);
                            Platform.runLater(LoginController::setTime);
                        }
                    };
                    timer.scheduleAtFixedRate(task,delay,period);
                }
            }else{
                isConnected.setText("Password wrong sorry please try again");
                Parent root = FXMLLoader.load(getClass().getResource("/sample/views/loginpage.fxml"));
                Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
            }
        }
        CatData.getInstance().close();
        UserData.getInstance().close();

    }

    @FXML
    void signup(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/views/signup.fxml"));
        Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    private static final void setTime(){
        if (Global.cat.getTimeToLive() == 1){
            Global.cat.isDied();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Game Over.");
            alert.setContentText("Please restart a new game");
            alert.show();

            timer.cancel();
            Platform.exit();


        }
    }
}
