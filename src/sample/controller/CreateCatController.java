package sample.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Cat;
import sample.Global;
import sample.TTL;
import sample.model.CatData;
import sample.Users;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class CreateCatController implements Initializable {
    public static long timeToLive;
    public static Timer timer;
    public static int second;

    public Users users = new Users();

    @FXML
    private TextField CatName;

    @FXML
    private ChoiceBox furcolour;

    @FXML
    private ChoiceBox eyecolour;

    @FXML
    private ChoiceBox personality;

    @FXML
    private ImageView showcat;

    @FXML
    private Label character;

    @FXML
    private Label name;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        furcolour.setValue("choose");
        furcolour.getItems().addAll("orange", "blue", "brown");
        eyecolour.getItems().addAll("yellow", "blue", "red");
        personality.getItems().addAll("shy", "high", "low");
        // TODO
    }

    public void confirmClicked(ActionEvent event) {
        String catname = CatName.getText();
        String fur = (String) furcolour.getSelectionModel().getSelectedItem();
        String eye = (String) eyecolour.getSelectionModel().getSelectedItem();
        Image catImage = Global.getInstance().setCatImage(fur,eye);
        showcat.setImage(catImage);
        character.setText(personality.getValue().toString());
        name.setText(catname);
    }

    @FXML
    void create(MouseEvent event) throws IOException{
        String name = CatName.getText();
        String furColour = (String) furcolour.getSelectionModel().getSelectedItem();
        String eyeColour = (String) eyecolour.getSelectionModel().getSelectedItem();
        String setPersonality = (String) personality.getSelectionModel().getSelectedItem();
        CatData.getInstance().open();
        Cat cat = new Cat(name);
        Global.setCat(cat);
        Global.cat.setFurColour(furColour);
        Global.cat.setEyeColour(eyeColour);
        Global.cat.setPersonality(setPersonality);
        String username = Global.getUsername();
        CatData.getInstance().setInsertCat(name,cat.getHappiness(),cat.getHunger(),
                cat.getLevel(),cat.getTimeOfBirth(),cat.getTimeToLive(), username,furColour,eyeColour,setPersonality);
        Parent root = FXMLLoader.load(getClass().getResource("/sample/views/home.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
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
                Platform.runLater(CreateCatController::setTime);

            }
        };
        timer.scheduleAtFixedRate(task,delay,period);
        CatData.getInstance().close();
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
