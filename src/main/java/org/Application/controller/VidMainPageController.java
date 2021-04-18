package org.Application.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import org.Application.Main;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * @author MCMainTank
 * @create 2021/4/15
 */
public class VidMainPageController implements Initializable{

    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
//    @FXML
//    private Button button5;
//    @FXML
//    private Button back;
//    @FXML
//    private MediaView mediaView;
//    @FXML
//    private AnchorPane anchorPane;


    public void initialize(URL location, ResourceBundle resources){
        button1.setOnAction((ActionEvent e) -> {
            System.out.println("Button 1 clicked");
        });


    }

    public void toVid1(ActionEvent event){System.out.println("Button 1 clicked");
        button1.setOnAction((ActionEvent e) -> {
            System.out.println("Button 1 clicked");
        });
        File file = new File("D:\\_Works\\SoftwareE\\Vid\\CSIS01E01.mp4");
        String url = file.toURI().toString();
        playVid(event,url);
    }

    public void toVid2(ActionEvent event){
        button2.setOnAction((ActionEvent e) -> {
            System.out.println("Button 2 clicked");
        });
        File file = new File("D:\\_Works\\SoftwareE\\Vid\\HTS01E01.mp4");
        String url = file.toURI().toString();
        playVid(event,url);
    }

    public void toVid3(ActionEvent event){
        button3.setOnAction((ActionEvent e) -> {
            System.out.println("clicked");
        });
    }

    public void toVid4(ActionEvent event){
        button4.setOnAction((ActionEvent e) -> {
            System.out.println("clicked");
        });
    }



    public void playVid(ActionEvent event, String url){


        Platform.runLater(()->{

            Stage primaryStage = (Stage) button1.getScene().getWindow();
            //primaryStage.hide();

            try{
                // Read file fxml and draw interface. new controller initialized from root;
                Parent root = FXMLLoader.load(Main.class
                        .getResource("Player.fxml"));
                Media media = new Media(url);
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                //Instantiating MediaView class
                MediaView mediaView = new MediaView(mediaPlayer);
//                mediaView.setMediaPlayer(mediaPlayer);
                //by setting this property to true, the Video will be played

                //setting group and scene
//                Group groupRoot = new Group();
//                groupRoot.getChildren().add(mediaView);
//                Scene scene = new Scene(groupRoot, 500, 400);
//                primaryStage.setScene(scene);
                Scene scene = new Scene(root);
//                ObservableList list =
//                anchorPane.getChildren();
//                Group groupRoot = new Group(mediaView);
//                list.add(mediaView);
                primaryStage.setTitle("Playing video");
                primaryStage.setScene(scene);

                primaryStage.show();

            }catch (Exception e){
                e.printStackTrace();
            }
        });



    }

    public void player(ActionEvent event, String url){


    }


}
