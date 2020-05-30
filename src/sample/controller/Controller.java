package sample.controller;


import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.Cat;
import sample.Global;
import sample.TTL;
import sample.Users;
import sample.model.CatData;
import sample.model.UserData;

import javax.xml.bind.annotation.XmlElementDecl;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class Controller implements Initializable {
    private double upperLimit = 0;
    private double[] limit=new double[20];
    private double lowerLimit=45;
    private int oneMinute = 60;
    private int second;


    Cat cat;


    @FXML
    private Label displayMunny;

    @FXML
    private Label displayHappiness;

    @FXML
    private Label displayHunger;

    @FXML
    private Label displayLevel;

    @FXML
    private Text displayTimeToLive;

    @FXML
    private ImageView showCat;

    @FXML
    public void about(MouseEvent event){
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss");
        Date resultdate = new Date(Global.cat.getTimeOfBirth());
        String timeOfBirth = sdf.format(resultdate);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setContentText("Username : " + Global.users.getUsername() + "\n"
                + "Cat Name : "+ Global.cat.getName() + "\n"
        + "Cat Time Of Birth : " +  timeOfBirth);
        alert.show();
    }


    @FXML
    public void playGame(MouseEvent event ){
        gameController gameController = new gameController();
        final Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        gameController.start(stage);
    }

    @FXML
    public void save(MouseEvent event){
        CatData.getInstance().open();
        UserData.getInstance().open();
//        System.out.println(Global.username + "" + Global.cat.getTimeToLive());
        boolean isSaved = CatData.getInstance().updateCat(Global.cat.getHappiness(),Global.cat.getHunger(),Global.cat.getLevel(),Global.cat.getTimeToLive(),Global.users.getUsername());
        boolean isCaved = UserData.getInstance().updateMunny(Global.users.getMunny().getMunnybalance(),Global.users.getUsername());
//        System.out.println(isSaved);
//        System.out.println(isCaved);
        CatData.getInstance().close();
        UserData.getInstance().close();
    }

    @FXML
    private void chatCat(MouseEvent event )throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/views/ChatRoomnew.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    private void feedCat(MouseEvent event )throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/views/feeding.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    public void sound(MouseEvent event) {
        String path = "C:\\Users\\alvin\\IdeaProjects\\MyPetCat\\src\\resources\\meow meow.mp3";
        Media media = new Media(new File(path).toURI().toString());
        AudioClip mediaplayer = new AudioClip(media.getSource());
        mediaplayer.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        showCat.setImage(Global.getInstance().setCatImage(Global.cat.getFurColour(), Global.cat.getEyeColour()));
        cat = Global.getCat();


        upLevel();


        System.out.println(upperLimit);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {

                        displayTimeToLive.setText(Long.toString(Global.cat.getTimeToLive()));
                        displayHappiness.setText(Double.toString(Global.getCat().getHappiness()));
                        displayHunger.setText(Double.toString(Global.getCat().getHunger()));
                        displayLevel.setText(Integer.toString(Global.getCat().getLevel()));
                        displayMunny.setText(Integer.toString(Global.getUsers().getMunny().getMunnybalance()));

                        second++;
                        hungry();
                        System.out.println(second);



//                        System.out.println(Global.cat.getTimeToLive());
                    }
                };
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Platform.runLater(updater);
                }
            }
        });
        thread.start();


    }

    public void upLevel(){
//        if(Global.cat.getHappiness() >= upperLimit && Global.cat.getHappiness()<= lowerLimit){
//            Global.cat.setLevel(Global.cat.getLevel()+1);
//            upperLimit*= 1.5;
//            lowerLimit= upperLimit*1.5;
//
//        }
        limit[0] = 20;
        for(int i = 1; i<20;i++){
            limit[i] = limit[i-1]*1.5;
        }
        for(int i = 19; i>=0; i--){
            if(Global.cat.getHappiness() > limit[i]){
                Global.cat.setLevel(i+1);
                break;
            }
        }
    }

    public void hungry(){
        if(second == oneMinute){
            Global.cat.setHunger(Global.cat.getHunger()+1);
            second=0;
        }
    }

}
