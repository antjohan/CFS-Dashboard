/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cfs.dashboard;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.animation.*;
import javafx.util.Duration;

public class DashboardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button Start;

    @FXML
    private Button Stop;
    @FXML
    private Label Sensor1_Output;
    @FXML
    private Label Sensor2_Output;
    @FXML
    private Label Sensor3_Output;
    @FXML
    private Label Sensor4_Output;
    @FXML
    private Label Sensor5_Output;
    @FXML
    private Label Sensor6_Output;
    @FXML
    private Label Sensor7_Output;
    @FXML
    private Label Sensor8_Output;
    @FXML
    private Label Sensor9_Output;
    @FXML
    private Label Sensor10_Output;
    @FXML
    private ComboBox Port;

    @FXML
    private Label ThreadStat;
    @FXML
    private Button findPorts;

    private PauseTransition uppdateAnimation;
    boolean animationRunning = false;
    boolean ThreadRunning = false;
    boolean ThreadSuspended = false;
    public String SelectedPort;

    @FXML
    private void handleButtonAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Start.setOnAction((event) -> {
            if (!ThreadRunning) {
                SelectedPort = (String) Port.getValue();
                Read_UART.setPort(SelectedPort);
                if (!animationRunning) {
                    setAnimation();
                    animationRunning = true;
                }
                Read_UART.Resume();
                ThreadStat.setText("Port Open");
                ThreadStat.setTextFill(Color.GREEN);
                Start.setText("Close");
                ThreadRunning = true;

                uppdateAnimation.play();

            } else {
                Read_UART.Stop();
                ThreadStat.setText("Port Closed");
                ThreadStat.setTextFill(Color.RED);
                Start.setText("Open");
                ThreadRunning = false;
                uppdateAnimation.stop();
            }
        });

        Stop.setOnAction((event) -> {
            Read_UART.Stop();
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
            uppdateAnimation.stop();

            Read_UART.Exit();
            ThreadStat.setTextFill(Color.RED);
            ThreadRunning = false;
            ThreadStat.setText("Thread terminated");
        });
        findPorts.setOnAction((event) -> {
            Read_UART.getPorts();
            Port.getItems().removeAll(Port.getItems());
            Port.getItems().addAll(Read_UART.portsList);

        });
    }

    private void setAnimation() {
        uppdateAnimation = new PauseTransition(Duration.millis(50));
        uppdateAnimation.setOnFinished((e) -> {
            update();
            uppdateAnimation.playFromStart();
        });
    }

    private void update() {
        if (Read_UART.uppdate) {
            Sensor1_Output.setText(Read_UART.output[0]);
            Sensor2_Output.setText(Read_UART.output[1]);
            Sensor3_Output.setText(Read_UART.output[2]);
            Sensor4_Output.setText(Read_UART.output[3]);
            Sensor5_Output.setText(Read_UART.output[4]);
            Sensor6_Output.setText(Read_UART.output[5]);
            Sensor7_Output.setText(Read_UART.output[6]);
            Sensor8_Output.setText(Read_UART.output[7]);
            Sensor9_Output.setText(Read_UART.output[8]);
            Sensor10_Output.setText(Read_UART.output[9]);
            Read_UART.uppdate = false;
        }
    }

}
