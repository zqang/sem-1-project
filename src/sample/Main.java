package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.controller.Controller;
import sample.model.CatData;
import sample.model.UserData;
import sun.audio.AudioPlayer;

import java.io.File;


public class Main extends Application {
    private Timeline timeline;

    @Override
    public void start(Stage primaryStage) throws Exception{
        String path = "C:\\Users\\alvin\\IdeaProjects\\MyPetCat\\src\\resources\\background music.mp3";
        Media media = new Media(new File(path).toURI().toString());
        AudioClip mediaplayer = new AudioClip(media.getSource());
        mediaplayer.play();
        Parent root = FXMLLoader.load(getClass().getResource("/sample/views/loginpage.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    @Override
    public void init() throws Exception {
        super.init();
        if(!UserData.getInstance().open()){
            System.out.println("FATAL ERROR: Couldn't connect to database");
            Platform.exit();
        }
        if(!CatData.getInstance().open()){
            System.out.println("FATAL ERROR: Couldn't connect to database");
            Platform.exit();
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        UserData.getInstance().close();
        CatData.getInstance().close();
    }


    public static void main(String[] args) {
        launch(args);
    }


}
