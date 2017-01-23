/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cfs.dashboard;

import com.fazecast.jSerialComm.SerialPort;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anton
 */
public class Read_UART implements Runnable {

    public void run() {
        Read();
    }
    static private boolean stop = false;
    static private boolean exit = false;

    static void Read() {
        SerialPort port = SerialPort.getCommPort("/dev/cu.usbmodem1411");
        while (true) {

            if (!stop) {
                port.openPort();
                port.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 100, 0);
                InputStream in = port.getInputStream();
                try {
                    while (true) {
                        System.out.print((char) in.read());
                        if (stop) {
                            break;
                        }
                    }
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                port.closePort();
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Read_UART.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (exit) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    static void Stop() {
        stop = true;
    }

    static void Resume() {
        stop = false;
    }

    static void Exit() {
        exit = true;
    }
}
