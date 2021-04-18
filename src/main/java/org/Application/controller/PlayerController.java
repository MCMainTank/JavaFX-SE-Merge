package org.Application.controller;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.Application.tools.csvTool;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author MCMainTank
 * @create 2021/4/18
 */
public class PlayerController implements Initializable {

    @FXML
    private Button button5;
    @FXML
    private Button back;
    @FXML
    private MediaView mediaView;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Slider volume;
    @FXML
    private Slider progressBar;
    @FXML
    private Label timeLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            String filepath = csvTool.readCurVid();
            File file = new File(filepath);
            String urlString = file.toURI().toString();
            Media media = new Media(urlString);
            MediaPlayer mediaPlayer = new MediaPlayer(media);
//            mediaPlayer.isAutoPlay();
            mediaView.setMediaPlayer(mediaPlayer);
            mediaPlayer.play();
            button5.setOnAction((ActionEvent e) -> {
                System.out.println("Button 5 clicked");
                System.out.println(button5.getText());
                if(button5.getText().equals("| |")){
                    button5.setText(">");
                    mediaPlayer.pause();
                }else if(button5.getText().equals(">")){
                    button5.setText("| |");
                    mediaPlayer.play();
                }
                System.out.println(mediaPlayer.bufferProgressTimeProperty().getValue());
            });

            volume.setValue(100);
            mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observableValue, Duration duration, Duration t1) {
                    updateTime(mediaPlayer);
                }
            });
            mediaPlayer.volumeProperty().bind(volume.valueProperty().divide(100));


        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public void back(){
        Stage primaryStage = (Stage) button5.getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass()
                    .getResource("../fxml/videoMainPage.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Playing video");
            primaryStage.setScene(scene);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private String formatTime(Duration elapsed, Duration duration) {
        //将两个Duartion参数转化为 hh：mm：ss的形式后输出
        int intElapsed = (int) Math.floor(elapsed.toSeconds());
        int elapsedHours = intElapsed / (60 * 60);
        int elapsedMinutes = (intElapsed - elapsedHours * 60 * 60) / 60;
        int elapsedSeconds = intElapsed - elapsedHours * 60 * 60 - elapsedMinutes * 60;
        if (duration.greaterThan(Duration.ZERO)) {
            int intDuration = (int) Math.floor(duration.toSeconds());
            int durationHours = intDuration / (60 * 60);
            int durationMinutes = (intDuration - durationHours * 60 * 60) / 60;
            int durationSeconds = intDuration - durationHours * 60 * 60 - durationMinutes * 60;

            if (durationHours > 0) {
                return String.format("%02d:%02d:%02d / %02d:%02d:%02d", elapsedHours, elapsedMinutes, elapsedSeconds, durationHours, durationMinutes, durationSeconds);
            } else {
                return String.format("%02d:%02d / %02d:%02d", elapsedMinutes, elapsedSeconds, durationMinutes, durationSeconds);
            }
        } else {
            if (elapsedHours > 0) {
                return String.format("%02d:%02d:%02d / %02d:%02d:%02d", elapsedHours, elapsedMinutes, elapsedSeconds);
            } else {
                return String.format("%02d:%02d / %02d:%02d", elapsedMinutes, elapsedSeconds);
            }
        }
    }
    public void updateTime(MediaPlayer mediaPlayer) {
        Duration duration=mediaPlayer.bufferProgressTimeProperty().getValue();
        if (progressBar != null && timeLabel != null) {
            Platform.runLater(()->{
                    Duration currentTime = mediaPlayer.getCurrentTime();
                    timeLabel.setText(formatTime(currentTime, duration));    //设置时间标签
                    progressBar.setDisable(duration.isUnknown());   //无法读取时间是隐藏进度条
                    if (!progressBar.isDisabled() && duration.greaterThan(Duration.ZERO) && !progressBar.isValueChanging()) {
                        progressBar.setValue(currentTime.toMillis() / duration.toMillis() * 100);   //设置进度条
                    }
                
            });
        }
    }


}
