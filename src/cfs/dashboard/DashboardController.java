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
    Read_UART UART = new Read_UART();
    Thread UART_Thread = new Thread(UART);
    boolean ThreadRunning = false;
    boolean ThreadSuspended = false;

    @FXML
    private void handleButtonAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Start.setOnAction((event) -> {
            if (!ThreadRunning) {
                UART_Thread.start();
                ThreadRunning = true;
            }
            Read_UART.Resume();
            ThreadStat.setText("Runnig");
            Stop.setText("Suspend");

            ThreadSuspended = false;

        });

        Stop.setOnAction((event) -> {
            if (ThreadSuspended) {
                //UART_Thread.interrupt();
                Read_UART.Exit();
                ThreadStat.setText("closed");

            } else {
                Read_UART.Stop();
                ThreadSuspended = true;
                ThreadStat.setText("Suspended");
                Stop.setText("Stop");

            }
            //UART_Thread.interrupt();
            System.out.println("1");

        });
    }

}
