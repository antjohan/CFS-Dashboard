/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cfs.dashboard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

public class DashboardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button Start;

    @FXML
    private Button Stop;
    @FXML
    private Label Output;

    @FXML
    private Label ThreadStat;

    boolean ThreadRunning = false;
    boolean ThreadSuspended = false;

    @FXML
    private void handleButtonAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Start.setOnAction((event) -> {
            if (!ThreadRunning) {
                Read_UART.Resume();
                ThreadStat.setText("Port Open");
                ThreadStat.setTextFill(Color.GREEN);
                Start.setText("Close");
                ThreadRunning = true;
            } else {
                Read_UART.Stop();
                ThreadStat.setText("Port Closed");
                ThreadStat.setTextFill(Color.RED);
                Start.setText("Open");
                ThreadRunning = false;
            }
        });

        Stop.setOnAction((event) -> {

            Read_UART.Exit();
            ThreadStat.setText("Thread terminated");
        });
    }

}
