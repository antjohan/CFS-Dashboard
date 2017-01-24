/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cfs.dashboard;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.*;

/**
 *
 * @author Anton
 */
public class CFSDashboard extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Read_UART UART = new Read_UART();
        Thread UART_Thread = new Thread(UART);
        UART_Thread.start();
        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("CFS Dashboard");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

    }

}
