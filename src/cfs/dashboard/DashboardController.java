/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cfs.dashboard;

import com.fazecast.jSerialComm.SerialPort;
import java.io.InputStream;
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
            System.out.println("Nexa 1 On");
            //udp.SendPacket("ND1M1");
            Read();
        });
    } 
    static void Read() {				
		SerialPort port = SerialPort.getCommPort("/dev/cu.usbmodem1411");
		port.openPort();
		port.openPort();
		port.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 100, 0);
		InputStream in = port.getInputStream();
		try
		{
			while(true){
				System.out.print((char)in.read());
				if(false)
					break;
			}
			in.close();
		} catch (Exception e) { e.printStackTrace(); }
		port.closePort();
	}
    
}
