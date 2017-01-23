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
import javafx.scene.control.Button;


public class DashboardController implements Initializable {

    /**
     * Initializes the controller class.
     */
        @FXML
    private Button Start;
    @FXML
    private void handleButtonAction(ActionEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Start.setOnAction((event) -> {
        Read_UART UART = new Read_UART();
        Thread UART_Thread = new Thread(UART);
        UART_Thread.start();
            System.out.println("Nexa 1 On");
         //udp.SendPacket("ND1M1");
        });
    }
}
