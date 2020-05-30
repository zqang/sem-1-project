package sample.controller;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.Global;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


public class gameController{



    Random random = new Random();
    long startNanoTime = System.nanoTime();
    ImageView cat = new ImageView();
    ImageView ren = new ImageView();
    ImageView backButton = new ImageView();
    int numberOfCoin=0;
    int getCoin;
    int coinNumber =0;
    double happinessNumber = 0;


    public boolean[][] map = new boolean[][]
            {{true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},
                    {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
                    {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
                    {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
                    {true,false,true,true,true,true,false,false,false,false,false,false,false,true,true,true,true,false,false,true},
                    {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
                    {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
                    {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
                    {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
                    {true,false,true,true,true,true,false,false,false,false,false,false,false,true,true,true,true,false,false,true},
                    {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
                    {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
                    {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
                    {true,false,false,false,true,true,true,true,false,false,false,false,false,false,false,false,false,false,false,true},
                    {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
                    {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
                    {true,false,false,false,false,false,false,false,false,false,false,true,true,true,true,false,false,false,false,true},
                    {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
                    {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
                    {true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true}};

    public boolean[][] wallArray = new boolean[][]
            {{true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},
    {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
    {true,false,true,true,true,true,false,true,true,true,true,false,false,false,true,false,true,true,false,true},
    {true,false,true,false,false,false,false,false,false,false,true,false,false,false,true,false,false,false,false,true},
    {true,false,true,false,true,true,true,true,true,false,true,false,true,true,true,false,true,true,false,true},
    {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true,false,false,true},
        {true,false,true,false,true,true,true,true,true,false,true,false,true,true,true,false,true,true,false,true},
        {true,false,true,false,false,false,false,false,false,false,true,false,false,false,true,false,false,false,false,true},
        {true,false,true,true,true,true,false,true,true,true,true,false,false,false,true,false,true,true,false,true},
        {true,false,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
        {true,false,true,false,true,false,false,false,false,false,false,false,false,true,true,false,false,false,false,true},
        {true,false,true,false,true,false,true,true,true,false,false,false,true,false,false,true,false,false,true,true},
        {true,false,false,false,true,false,true,false,true,false,false,true,false,false,false,false,true,false,false,true},
        {true,false,true,false,true,false,true,false,true,true,true,false,false,false,false,true,false,false,false,true},
        {true,false,true,false,true,false,false,false,false,false,false,false,false,false,true,false,false,true,true,true},
        {true,false,true,false,false,false,true,false,true,true,true,false,false,false,false,true,false,false,false,true},
        {true,false,false,false,true,false,true,false,true,false,false,true,false,false,false,false,true,false,false,true},
        {true,false,false,false,true,false,true,true,true,false,false,false,true,false,false,true,false,false,true,true},
        {true,false,true,false,false,false,false,false,false,false,false,false,false,true,true,false,false,false,false,true},
        {true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true}};


        public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        Image backImage = new Image("/resources/136573_button_256x256.png",50,50,true,true);
        backButton.setImage(backImage);
        backButton.setOnMousePressed(setOnMouseClicked);
        gridPane.add(backButton,0,1);
        Image fishImage = new Image(Global.WALKINGCAT,50,50,true,true);
        cat.setImage(fishImage);
        gridPane.add(cat,1,1);
        cat.setFocusTraversable(true);
        cat.setOnKeyPressed(setOnTheKeyPressed);
        Image renImage = new Image("/resources/220px-User_icon_2.svg.png",50,50,true,true);
        ren.setImage(renImage);
        gridPane.add(ren,1,0);
        Scene scene = new Scene(gridPane, 1000, 1000);
        primaryStage.setScene( scene );
        primaryStage.setTitle("Game");


        ArrayList<ImageView> coinList = new ArrayList<>();
        Image coinImage = new Image(Global.COIN,50,50,true,true);
        Image wall = new Image(Global.WALL,50,50,true,true);
        ImageView[][] imageViews = new ImageView[20][20];
        for(int i = 0; i<20 ;i++){
            for(int j = 0; j<20 ; j++){
                if (wallArray[i][j]){
                    imageViews[i][j] = new ImageView();
                    imageViews[i][j].setImage(wall);
                    gridPane.add(imageViews[i][j],j,i);
                }
            }
        }
        for(int i = 0; i<20 ;i++){
            for(int j = 0; j<20 ; j++){
                if (!wallArray[i][j]){
                    getCoin = random.nextInt(4);
                    switch (getCoin){
                        case 0:
                        break;
                        case 1: {ImageView coin = new ImageView();
                            coin.setImage(coinImage);
                            coinList.add(coin);
                            gridPane.add(coin,j,i);
                            numberOfCoin++;
                            break;}
                        case 2:break;
                        case 3:break;
                    }
                }
            }
        }

        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                //calculate time since last update
                double elapsedTime = (currentNanoTime - startNanoTime)/1000000000.0;
                startNanoTime = currentNanoTime;

                Iterator<ImageView> coinIter = coinList.iterator();
                while (coinIter.hasNext()){
                    ImageView coin = coinIter.next();
                    if (GridPane.getRowIndex(coin) == GridPane.getRowIndex(cat) && GridPane.getColumnIndex(coin) == GridPane.getColumnIndex(cat)){
                        coinIter.remove();
                        coin.setImage(null);
                        numberOfCoin--;
                        coinNumber++;
                        if(coinNumber%5==0){
                        happinessNumber+=1;
                        Global.cat.setHappiness(Global.cat.getHappiness()+1);}
                        Global.users.getMunny().gainMunny(1);
                        System.out.println(numberOfCoin);

                    }
                }
                if(numberOfCoin==0){
                    this.stop();
                    showAlert();
                }
            }
        };
        gameLoop.start();
        primaryStage.show();
    }


    EventHandler<KeyEvent> setOnTheKeyPressed =
            new EventHandler<KeyEvent>() {

                @Override
                public void handle(KeyEvent event)
                {
                    int orgRowCat =GridPane.getRowIndex(cat);
                    int orgColumnCat =GridPane.getColumnIndex(cat);

                    if ( event.getCode()== KeyCode.UP ){
                        if(wallArray[orgRowCat-1][orgColumnCat]){

                        }else{
                            GridPane.setRowIndex(cat,orgRowCat-1);
                            GridPane.setRowIndex(ren,orgRowCat);
                            GridPane.setColumnIndex(ren,orgColumnCat);
                        }

                    }


                    if ( event.getCode()== KeyCode.DOWN ){
                        if(wallArray[orgRowCat+1][orgColumnCat]){

                        }else{
                            GridPane.setRowIndex(cat,orgRowCat+1);
                            GridPane.setRowIndex(ren,orgRowCat);
                            GridPane.setColumnIndex(ren,orgColumnCat);

                        }
                    }

                    if ( event.getCode()== KeyCode.RIGHT ){
                        if(wallArray[orgRowCat][orgColumnCat+1]){

                        }else{
                            GridPane.setColumnIndex(cat,orgColumnCat+1);
                            GridPane.setRowIndex(ren,orgRowCat);
                            GridPane.setColumnIndex(ren,orgColumnCat);

                        }
                    }


                    if ( event.getCode()== KeyCode.LEFT ){
                        if(wallArray[orgRowCat][orgColumnCat-1]){
                        }else{
                            GridPane.setColumnIndex(cat,orgColumnCat-1);
                            GridPane.setRowIndex(ren,orgRowCat);
                            GridPane.setColumnIndex(ren,orgColumnCat);

                        }
                    }
                }
            };

    EventHandler<MouseEvent> setOnMouseClicked = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event)  {
            try{
                Parent root = FXMLLoader.load(getClass().getResource("/sample/views/home.fxml"));
                Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    };


    public void showAlert(){
        if (numberOfCoin== 0) {
            //computer lost!
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Congratulation.You have collected all the money.");
            alert.setContentText("Total of " + coinNumber + " money collected.\n" + "Total of " + happinessNumber + " happiness collected.");
            alert.show();
        }
    }

}

