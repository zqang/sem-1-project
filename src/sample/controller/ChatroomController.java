package sample.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Chat;
import sample.Global;

import static sample.model.UserData.COLUMN_USER_EMAIL;
import static sample.model.UserData.LOGIN_USER;
import static sample.model.UserData.TABLE_USER;

public class ChatroomController implements Initializable {
    
    @FXML
    private VBox textmessage;
    @FXML
 //   private AnchorPane anchorPane;
    private ScrollPane scrollPane;
 
    @FXML private ChoiceBox messageinput;
    @FXML private TextField textField;
    @FXML private Label meaning;
    @FXML private Button sendButton;
    String language = "SELECT meowlanguage , meowlanguage2 FROM meowlanguage.cat WHERE `question` =\"";


    /* public void displaymessage(ActionEvent event) {
        TextField textfield = new TextField();
        textfield.setText("hello");
        textmessage.getChildren().add(textfield);

    }*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<Chat> Chat = new ArrayList<>();
//        String sql ="select*from meowlanguage where level =?";
//        StringBuilder sb = new StringBuilder();
//        sb.append(sql);
//        String level = Integer.toString(Global.cat.getLevel());
//        System.out.println(Global.cat.getLevel());
//        sb.append(level);
        
        try {
            // Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost /meowlanguage?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "nyz20001020");
            Connection myConn = DriverManager.getConnection("jdbc:sqlite:C:/Users/alvin/IdeaProjects/MyPetCat/meowlanguage.db");
            Statement myStmt = myConn.createStatement();
            ResultSet myrs = myStmt.executeQuery("select*from meowlanguage");
            if (Global.cat.getLevel() == 1) {
                myrs = myStmt.executeQuery("select*from meowlanguage where level =1 ");
                while (myrs.next()) {
                    Chat chats = new Chat();
                    chats.setQuestion(myrs.getString("question"));
                    messageinput.getItems().add(myrs.getString("question"));
                }
            }
            if (Global.cat.getLevel() == 2) {
                myrs = myStmt.executeQuery("select*from meowlanguage where level =1 OR level = 2 ");
                while (myrs.next()) {
                    Chat chats = new Chat();
                    chats.setQuestion(myrs.getString("question"));
                    messageinput.getItems().add(myrs.getString("question"));
                }
            }
            if (Global.cat.getLevel() == 3) {
                myrs = myStmt.executeQuery("select*from meowlanguage where level =1 OR level = 2 OR level = 3 ");
                while (myrs.next()) {
                    Chat chats = new Chat();
                    chats.setQuestion(myrs.getString("question"));
                    messageinput.getItems().add(myrs.getString("question"));
                }
            }
            if (Global.cat.getLevel() == 4) {
                myrs = myStmt.executeQuery("select*from meowlanguage where level =1 OR level = 2 OR level = 3 OR level=4");
                while (myrs.next()) {
                    Chat chats = new Chat();
                    chats.setQuestion(myrs.getString("question"));
                    messageinput.getItems().add(myrs.getString("question"));
                }
            }
            if (Global.cat.getLevel() == 5) {
                myrs = myStmt.executeQuery("select*from meowlanguage where level =1 OR level = 2 OR level = 3 OR level=4 OR level = 5");
                while (myrs.next()) {
                    Chat chats = new Chat();
                    chats.setQuestion(myrs.getString("question"));
                    messageinput.getItems().add(myrs.getString("question"));
                }
            }
            if (Global.cat.getLevel() == 6) {
                myrs = myStmt.executeQuery("select*from meowlanguage where level =1 OR level = 2 OR level = 3 OR level=4 OR level = 5 OR level=6");
                while (myrs.next()) {
                    Chat chats = new Chat();
                    chats.setQuestion(myrs.getString("question"));
                    messageinput.getItems().add(myrs.getString("question"));
                }
            }
            if (Global.cat.getLevel() == 7) {
                myrs = myStmt.executeQuery("select*from meowlanguage where level =1 OR level = 2 OR level = 3 OR level=4 OR level = 5 OR level=6 OR level=7");
                while (myrs.next()) {
                    Chat chats = new Chat();
                    chats.setQuestion(myrs.getString("question"));
                    messageinput.getItems().add(myrs.getString("question"));
                }
            }
            if (Global.cat.getLevel() == 8) {
                myrs = myStmt.executeQuery("select*from meowlanguage where level =1 OR level = 2 OR level = 3 OR level=4 OR level = 5 OR level=6 OR level=7 OR level=8");
                while (myrs.next()) {
                    Chat chats = new Chat();
                    chats.setQuestion(myrs.getString("question"));
                    messageinput.getItems().add(myrs.getString("question"));
                }
            }
            if (Global.cat.getLevel() == 9) {
                myrs = myStmt.executeQuery("select*from meowlanguage where level =1 OR level = 2 OR level = 3 OR level=4 OR level = 5 OR level=6 OR level=7 OR level=8 OR level=9");
                while (myrs.next()) {
                    Chat chats = new Chat();
                    chats.setQuestion(myrs.getString("question"));
                    messageinput.getItems().add(myrs.getString("question"));
                }
            }
            if (Global.cat.getLevel() == 10) {
                myrs = myStmt.executeQuery("select*from meowlanguage where level =1 OR level = 2 OR level = 3 OR level=4 OR level = 5 OR level=6 OR level=7 OR level=8 OR level=9 OR level=10");
                while (myrs.next()) {
                    Chat chats = new Chat();
                    chats.setQuestion(myrs.getString("question"));
                    messageinput.getItems().add(myrs.getString("question"));
                }
            }

            //ArrayList<String> meowlanguage=new ArrayList<>();

//            while (myrs.next()) {
//                Chat chats = new Chat();
//                chats.setLevel(myrs.getInt("level"));
//                chats.setQuestion(myrs.getString("question"));
//                Chat.add(chats);
//            }


        } catch (Exception exc) {
            exc.printStackTrace();
        }
//        for(Chat chat1 : Chat){
//            if(chat1.getLevel()<=Global.cat.getLevel()) {
//                messageinput.getItems().add(chat1.getQuestion());
//
//            }}
            }
        //    String s=Chat.question;
        //  messageinput.getItems().addAll(Chat.question);

    
    public void sendmessage(ActionEvent Event) throws SQLException {
        TextField textView = new TextField();
        textView.setText((String) messageinput.getValue());
        textmessage.getChildren().add(textView);
        String CONNECTION_STRING = "jdbc:sqlite:C:/Users/alvin/IdeaProjects/MyPetCat/meowlanguage.db";
        Connection myConn = DriverManager.getConnection(CONNECTION_STRING);
        //Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost /meowlanguage?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "nyz20001020");
        Statement myStmt = myConn.createStatement();
        StringBuilder sb = new StringBuilder(language);
        String question = (String) messageinput.getValue();
        sb.append(question);
        sb.append("\"");
        ResultSet myrs = myStmt.executeQuery("select*from meowlanguage where question ='" + question + "'");
        Chat chats = new Chat();
        
        while (myrs.next()) {
            chats.setMeowlanguage(myrs.getString("meowlanguage1"));
            chats.setMeowlanguage2(myrs.getString("meowlanguage2"));
        }
        Random r = new Random();
        int num = r.nextInt(2);
        TextField textfield1 = new TextField();
        if (num == 1) {
            textfield1.setText(chats.getMeowlanguage());
            textmessage.getChildren().add(textfield1);
        } else {
            textfield1.setText(chats.getMeowlanguage2());
            textmessage.getChildren().add(textfield1);
        }
        messageinput.setValue(null);
    }
    public void searchButtonClicked(ActionEvent Event) throws SQLException{
        textField.getText();
        String text=textField.getText();
        String CONNECTION_STRING = "jdbc:sqlite:C:/Users/alvin/IdeaProjects/MyPetCat/meowlanguage.db";
        Connection myConn = DriverManager.getConnection(CONNECTION_STRING);
        //Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost /converter?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "nyz20001020");
        Statement myStmt = myConn.createStatement();
        ResultSet myrs = myStmt.executeQuery("select*from converter where meowlanguage ='" + text+ "'" );
        Chat chats=new Chat();
        while(myrs.next()){
            chats.setMeaning(myrs.getString("meaning"));
        }
        meaning.setText(chats.getMeaning());
    }

    public void back(MouseEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/views/home.fxml"));
        Stage stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}
