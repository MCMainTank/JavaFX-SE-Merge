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
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.Application.Main;
import org.Application.tools.csvTool;
import org.Application.vo.Trainer;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class LiveController implements Initializable {
    @FXML
    public Button LiveBtn;
    @FXML
    private Text trainer;
    @FXML
    private Text nextTime1;
    @FXML
    private Text nextTime2;
    @FXML
    private Text nextTime3;
    @FXML
    private Button JoinBtn;
    @FXML
    private Text LiveTime;
    @FXML
    private ChoiceBox DateBox;
    @FXML
    private Button BookBtn;
    @FXML
    private Text Target;
    @FXML
    private Text BMI;
    @FXML
    private Button Videos;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] stu = csvTool.readCur("CurUser");
        double body = Double.parseDouble(stu[7]) / (Double.parseDouble(stu[6]) * Double.parseDouble(stu[6]) / 10000);
        BigDecimal b = new BigDecimal(body);
        body = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        BMI.setText(String.valueOf(body));

        String[] lives = csvTool.readCur("CurLive");
        String[] date1 = lives[2].split(" ");
        String[] date2 = lives[5].split(" ");
        String[] date3 = lives[8].split(" ");
        String Tid = lives[1];

        nextTime1.setText(date1[0]+"day"+" "+date1[1]);
        nextTime2.setText(date2[0]+"day"+" "+date2[1]);
        nextTime3.setText(date3[0]+"day"+" "+date3[1]);

        Trainer[] tra = csvTool.searchTrainer(Tid,0);
        assert tra != null;
        String name = tra[0].getName();
        trainer.setText(name);
        Target.setText(tra[0].getStrength());

        DateBox.getItems().addAll(date1[0]+"day"+"    "+date1[1],date2[0]+"day"+"    "+date2[1],date3[0]+"day"+"    "+date3[1]);
        DateBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                csvTool.cleanCur("CurBook");
                csvTool.write("CurBook",new String[]{lives[newValue.intValue() * 3],lives[1+newValue.intValue() * 3],lives[2+newValue.intValue() * 3]});
            }
        });

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

    public void BookLive(ActionEvent event){
        Platform.runLater(()->{
            Stage primaryStage = (Stage) BookBtn.getScene().getWindow();
            //primaryStage.hide();
            try{

                // Read file fxml and draw interface. new controller initialized from root;
                Parent root = FXMLLoader.load(Main.class
                        .getResource("WaitLive.fxml"));

                primaryStage.setTitle("My WaitLive Application1");
                primaryStage.setScene(new Scene(root));
                primaryStage.show();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }


    public void toVid() throws IOException {
            Stage newStage = (Stage)Videos.getScene().getWindow();
            newStage.setScene(new Scene(FXMLLoader.load(Main.class
                    .getResource("videoMainPage.fxml"))));
    }

}


