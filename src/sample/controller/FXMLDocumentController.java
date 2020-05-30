/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author USER
 */
public abstract class FXMLDocumentController implements Initializable {
    Random r=new Random();
    ImageView cat = new ImageView();
    ImageView rat = new ImageView();
    ImageView bird = new ImageView();
    int numofatk;
    public boolean[][] houseArray= new boolean[][]
    {{false,false,false},
        {false,false,false},
        {false,false,false},
        {false,false,false},
        {false,false,false},
        {false,false,false},
        {false,false,false},
        {false,false,false},
        {false,false,false},
        {false,false,false},
        {true,true,true}};
    
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        Image catImage = new Image("cat.png");
        cat.setImage(catImage);
        gridPane.add(cat,9,1);
        cat.setFocusTraversable(true);
        //cat.setOnKeyPressed(setOnTheKeyPressed);
        Scene scene = new Scene(gridPane, 1000, 1000);
        primaryStage.setScene( scene );
        primaryStage.setTitle("Game");
        
        
        Image birdImage = new Image("bird.png");
        Image ratImage = new Image("rat.png");
        Image houseImage = new Image("house.png");
        ImageView[][] imageViews = new ImageView[11][3];
        int atk;
        for(int i=0;i<9;i++){
            for(int j = 0;j<3;j++){
                atk=r.nextInt(5);
                switch (atk){
                    case 0:{break;}
                    case 1:{
                       if(!houseArray[i][j]){
                           imageViews[i][j]=new ImageView();
                           imageViews[i][j].setImage(ratImage);
                           gridPane.add(imageViews[i][j],j,i);}
                    break;}
                    case 2:{break;}
                    case 3:{
                       if(!houseArray[i][j]){
                           imageViews[i][j]=new ImageView();
                           imageViews[i][j].setImage(birdImage);
                           gridPane.add(imageViews[i][j],j,i);}
                    break;}
                    case 4:{break;}
            }}}
        for(int i=10;i<11;i++){
            for(int j=0;j<3;j++){
                imageViews[i][j]=new ImageView();
                imageViews[i][j].setImage(houseImage);
                gridPane.add(imageViews[i][j],j,i);
            }
        }
        int numberOfCoin = 0;
        if(numberOfCoin==0){
            System.out.println("you have won the game");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Current project is modified");
            alert.setContentText("Save?");
            ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType noButton = new ButtonType("Yes", ButtonBar.ButtonData.NO);
            ButtonType cancelButton = new ButtonType("Yes", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(okButton, noButton, cancelButton);
            alert.showAndWait().ifPresent(type -> {
                if (type == ButtonType.OK) {
                } else if (type == ButtonType.NO) {
                } else {
                }
            });
        }
        primaryStage.show();
    }
    

    EventHandler<MouseEvent> onMouseClicked = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            try{
                back(event);
            }catch (IOException e){
                System.out.println(e.getMessage() + "back error");
            }

        }
    };



    EventHandler<KeyEvent> setOnTheKeyPressed =
            new EventHandler<KeyEvent>() {

                @Override
                public void handle(KeyEvent event)
                {
                    int orgRowCat =GridPane.getRowIndex(cat);
                    int orgColumnCat =GridPane.getColumnIndex(cat);

                    if ( event.getCode()== KeyCode.RIGHT ){
                        GridPane.setColumnIndex(cat,orgColumnCat+1);
                    }


                    if ( event.getCode()== KeyCode.LEFT ){
                        GridPane.setColumnIndex(cat,orgColumnCat-1);
                    }
                }
            };


    public void back(MouseEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/views/main.fxml"));
        Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
    }

