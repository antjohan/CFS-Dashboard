/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cfs.dashboard;

import com.fazecast.jSerialComm.SerialPort;
import java.io.InputStream;

/**
 *
 * @author Anton
 */
public class Read_UART implements Runnable {
    public void run() {
        Read();
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
