package org.Application.controller;

import javafx.fxml.FXML;
import org.Application.Main;

import java.io.IOException;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        Main.setRoot("primary");
    }
}