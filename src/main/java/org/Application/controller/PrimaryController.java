package org.Application.controller;

import javafx.fxml.FXML;
import org.Application.Main;

import java.io.IOException;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        Main.setRoot("secondary");
    }
}
