package org.Application.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.Application.Main;
import org.Application.tools.csvTool;
import org.Application.vo.Live;
import org.Application.vo.Student;
import org.Application.vo.Trainer;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Button myButton;
    @FXML
    private Button SignUpButton;
    @FXML
    private TextField myTextField;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO (don't really need to do anything here).

    }

    // When user click on myButton
    // this method will be called.
    public void showDateTime(ActionEvent event) {

        Date now= new Date();

        DateFormat df = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
        String dateTimeString = df.format(now);
        // Show in VIEW
        myTextField.setText(dateTimeString);
        System.out.println("fuck");

    }

    public void toSign(ActionEvent event){
        Platform.runLater(()->{
            Stage primaryStage = (Stage) SignUpButton.getScene().getWindow();
            //primaryStage.hide();
            try{

                // Read file fxml and draw interface. new controller initialized from root;
                Parent root = FXMLLoader.load(Main.class
                        .getResource("ChooseLive.fxml"));

                primaryStage.setTitle("My sample Application1");
                primaryStage.setScene(new Scene(root));
                primaryStage.show();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    public void Login(ActionEvent event){
        String name = username.getText();
        String word = password.getText();
        //System.out.println(username.getText()+" "+password.getText());
        Student[] stu = csvTool.searchStu(name);
        if(stu==null) {
            Alert error = new Alert(Alert.AlertType.ERROR,"Wrong Password or Username");
            //javafx alert test
            ButtonType buttonTypeOne = new ButtonType("One");
            ButtonType buttonTypeTwo = new ButtonType("Two");
            ButtonType buttonTypeThree = new ButtonType("Three");
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            error.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree, buttonTypeCancel);

            Optional<ButtonType> result = error.showAndWait();
            if(result.get() == buttonTypeOne){
                System.out.println("1");
            }

            return;
        }
        if(Objects.equals(stu[0].getPass(), word)) {
            System.out.println("ok");
            csvTool.cleanCur("CurUser");
            csvTool.write("CurUser",new String[]{String.valueOf(stu[0].getId()),stu[0].getName(),stu[0].getMail(),stu[0].getPass(),stu[0].getGender(),
                    String.valueOf(stu[0].getYear()), String.valueOf(stu[0].getHeight()), String.valueOf(stu[0].getWeight()), String.valueOf(stu[0].getLevel()),stu[0].getDate(), String.valueOf(stu[0].getTid())});

            Trainer[] tra = csvTool.searchTrainer(String.valueOf(stu[0].getTid()),0);
            assert tra != null;
            csvTool.cleanCur("CurTra");
            csvTool.write("CurTra",new String[]{String.valueOf(tra[0].getTid()),tra[0].getMail(),tra[0].getPassword(),tra[0].getGender(),tra[0].getName(),tra[0].getStrength()});

            Live[] live = csvTool.searchLive(stu[0].getTid(),1);
            assert live != null;
            csvTool.cleanCur("CurLive");
            csvTool.write("CurLive",new String[]{String.valueOf(live[0].getSid()), String.valueOf(live[0].getTid()),live[0].getDate()});
            csvTool.write("CurLive",new String[]{String.valueOf(live[1].getSid()), String.valueOf(live[1].getTid()),live[1].getDate()});
            csvTool.write("CurLive",new String[]{String.valueOf(live[2].getSid()), String.valueOf(live[2].getTid()),live[2].getDate()});

            toSign(event);
            return;
        }
    }



}


