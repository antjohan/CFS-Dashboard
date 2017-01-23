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

    public static String input;

    public void run() {
        Read();
    }
    static private boolean stop = true;
    static private boolean exit = false;

    static void Read() {
        SerialPort[] Ports = SerialPort.getCommPorts();
        for (int i = 0; i < Ports.length; i++) {
            String strPorts = Ports[i].getDescriptivePortName();
            System.out.println(strPorts);
        }
        SerialPort port = Ports[4];
        while (true) {

            if (!stop) {
                port.openPort();
                port.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 100, 0);
                InputStream in = port.getInputStream();
                try {
                    while (true) {
                        char str = (char)in.read();
                        input = input + str;
                        if(input.contains("\n")){
                           System.out.print(input);
                           input = "";

                        }
                        //System.out.print((char) in.read());
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
        stop = false;
        exit = true;
    }
}
