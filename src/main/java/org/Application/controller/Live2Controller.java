package org.Application.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.Application.Main;
import org.Application.tools.csvTool;
import org.Application.vo.Trainer;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Live2Controller implements Initializable {
    @FXML
    public Button LiveBtn;
    @FXML
    private Text trainer;
    @FXML
    private Button JoinBtn;
    @FXML
    private Text LiveTime;

    @FXML
    private Text Target;
    @FXML
    private Text BMI;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] stu = csvTool.readCur("CurUser");
        double body = Double.parseDouble(stu[7]) / (Double.parseDouble(stu[6]) * Double.parseDouble(stu[6]) / 10000);
        BigDecimal b = new BigDecimal(body);
        body = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        BMI.setText(String.valueOf(body));

        String[] lives = csvTool.readCur("CurBook");
        String[] date1 = lives[2].split(" ");
        String Tid = lives[1];

        LiveTime.setText(date1[0]+"day"+"    "+date1[1]);

        Trainer[] tra = csvTool.searchTrainer(Tid,0);
        assert tra != null;
        String name = tra[0].getName();
        trainer.setText(name);
        Target.setText(tra[0].getStrength());

    }

    public void levelCheck(ActionEvent event){
        String[] a = csvTool.readCur("CurUser");
        if(Integer.parseInt(a[8]) < 3){
            Alert error = new Alert(Alert.AlertType.ERROR,"Sorry,you need VIP so that you can see the live.");
            //javafx alert test
            ButtonType buttonTypeOne = new ButtonType("Buy it!");
            ButtonType buttonTypeTwo = new ButtonType("Buy it!");
            ButtonType buttonTypeThree = new ButtonType("Buy it!");
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            error.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree, buttonTypeCancel);

            Optional<ButtonType> result = error.showAndWait();
            if(result.get() == buttonTypeOne){
                System.out.println("Buy it!");
            }
        }
        else{
            Alert congra = new Alert(Alert.AlertType.CONFIRMATION,"VIP SAMA DAISUKI❤");
            congra.showAndWait();
        }

    }

    public void JoinLive(ActionEvent event){
        Platform.runLater(()->{
            Stage primaryStage = (Stage) JoinBtn.getScene().getWindow();
            //primaryStage.hide();
            try{
                File file = new File("D:\\_Works\\SoftwareE\\Vid\\CSIS01E01.mp4");
                String url = file.toURI().toString();
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

}


