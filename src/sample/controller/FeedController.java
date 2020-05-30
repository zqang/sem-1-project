package sample.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import sample.Food;
import sample.Global;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class FeedController implements Initializable {
    @FXML
    private ImageView imgFish;

    @FXML
    private ImageView imgWater;

    @FXML
    private ImageView imgWaterFish;

    @FXML
    private ImageView showcat;

    @FXML
    private Label displayMunny;

    @FXML
    private Label displayHunger;


    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
    double catImgX,catImgY ;
    double newTranslateX, newTranslateY;
    double orgLayoutX,orgLayoutY;
    Food fish = new Food("fish",7,7,7);
    Food water = new Food("water",5,5,5);
    Food fishAndWater = new Food("FishAndWater",10,10,10);


     EventHandler<MouseEvent> foodOnMouseDraggedEventHandler =
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    double offsetX = event.getSceneX() - orgSceneX;
                    double offsetY = event.getSceneY() - orgSceneY;
                    newTranslateX = orgTranslateX + offsetX;
                    newTranslateY = orgTranslateY + offsetY;
                    ((ImageView)(event.getSource())).setTranslateX(newTranslateX);
                    ((ImageView)(event.getSource())).setTranslateY(newTranslateY);
                }
            };

    EventHandler<MouseEvent> foodOnMousePressedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    orgSceneX = event.getSceneX();
                    orgSceneY = event.getSceneY();
                    orgTranslateX = ((ImageView)(event.getSource())).getTranslateX();
                    orgTranslateY = ((ImageView)(event.getSource())).getTranslateY();

                }
            };

    EventHandler<MouseEvent> foodOnMouseReleasedEventHandler =
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    orgLayoutX = ((ImageView)(event.getSource())).getLayoutX();
                    orgLayoutY = ((ImageView)(event.getSource())).getLayoutY();
                    double newX = (orgLayoutX + newTranslateX)-catImgX;
                    double newY = (orgLayoutY + newTranslateY)-catImgY;
                    if (Math.abs(newX)<5 || Math.abs(newY)<5){
                        System.out.println(orgTranslateX);
                        System.out.println(((ImageView)(event.getSource())).getTranslateX());
                        System.out.println(orgLayoutX);
                        System.out.println(newX);
                        System.out.println();
                        ((ImageView)(event.getSource())).setTranslateY(newTranslateX-newTranslateX);
                        ((ImageView)(event.getSource())).setTranslateX(newTranslateY-newTranslateY);

                        if(((ImageView)(event.getSource())) == imgFish){
                            eatFood(fish);
                            displayHunger.setText(Double.toString(Global.cat.getHunger()));
                            displayMunny.setText(Integer.toString(Global.users.getMunny().getMunnybalance()));
                        }else if (((ImageView)(event.getSource())) == imgWater){
                            eatFood(water);
                            displayHunger.setText(Double.toString(Global.cat.getHunger()));
                            displayMunny.setText(Integer.toString(Global.users.getMunny().getMunnybalance()));
                        }else if (((ImageView)(event.getSource())) == imgWaterFish){
                            eatFood(fishAndWater);
                            displayHunger.setText(Double.toString(Global.cat.getHunger()));
                            displayMunny.setText(Integer.toString(Global.users.getMunny().getMunnybalance()));
                        }


//                        ((ImageView)(event.getSource())).setTranslateX(orgLayoutX);
//                        ((ImageView)(event.getSource())).setTranslateY(orgLayoutY);

                    }
                }
            };


    private void eatFood(Food food){
        if(Global.users.getMunny().getMunnybalance()>=food.getPrice()){
            Global.cat.eatFood(food);
            Global.users.getMunny().lossMunny(food.getPrice());
        }else return;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setImageMove(imgFish);
        setImageMove(imgWater);
        setImageMove(imgWaterFish);
        showcat.setImage(Global.getInstance().setCatImage(Global.cat.getFurColour(),Global.cat.getEyeColour()));
        catImgX = showcat.getLayoutX();
        catImgY = showcat.getLayoutY();
    }

    private void setImageMove(ImageView image){
        image.setCursor(Cursor.HAND);
        image.setOnMousePressed(foodOnMousePressedEventHandler);
        image.setOnMouseDragged(foodOnMouseDraggedEventHandler);
        image.setOnMouseReleased(foodOnMouseReleasedEventHandler);
    }



    public void backToMain(MouseEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/views/home.fxml"));
        Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}
