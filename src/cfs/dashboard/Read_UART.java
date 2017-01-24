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
import java.util.*;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 *
 * @author Anton
 */
public class Read_UART implements Runnable {

    public static String input;
    public static String output[] = new String[10];
    public static boolean uppdate = false;
    public static ArrayList portsList = new ArrayList();
    static String ComPort;

    public void run() {
        Read();
    }
    static private boolean stop = true;
    static private boolean exit = false;

    static void Read() {
//SerialPort port = Ports[4];
        while (true) {

            if (!stop) {
                //setPort("IOUSBHostDevice");
                SerialPort[] Ports = SerialPort.getCommPorts();
                SerialPort port = null;
                for (SerialPort Port : Ports) {
                    String strPorts = Port.getDescriptivePortName();
                    if (strPorts.compareToIgnoreCase(ComPort) == 0) {
                        port = Port;
                    }
                }                
                port.openPort();
                port.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 100, 0);
                InputStream in = port.getInputStream();
                try {
                    while (true) {
                        char str = (char) in.read();
                        input = input + str;
                        if (input.contains("\n")) {
                            int i = 0;
                            while (input.contains("-")) {
                                int index = input.indexOf("-");
                                output[i] = input.substring(0, index);
                                input = input.substring(index + 1, input.length());
                                i++;
                            }
                            output[i] = input;
                            uppdate = true;
                            input = "";
                        }
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

    static void getPorts() {
        SerialPort[] Ports = SerialPort.getCommPorts();
        portsList.removeAll(portsList);
        for (SerialPort Port : Ports) {
            String strPort = Port.getDescriptivePortName();
            portsList.add(strPort);
        }
    }

    static void setPort(String str) {
        ComPort = str;
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
