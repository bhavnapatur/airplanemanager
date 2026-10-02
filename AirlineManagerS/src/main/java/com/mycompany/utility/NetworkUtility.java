/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.utility;

/**
 *
 * @author 227709
 */
/*Responibility:
* Used to find an first available port incase 7070 is in use.
* Other than package information, this class should not be modified for projects.
* This utility is useful when ports are not release when programs error out.
*/


import java.io.IOException;
import java.net.ServerSocket;

public class NetworkUtility {

    //Method to check if a port number is available
    public static boolean isPortAvailable(int port) {
        try (ServerSocket socket = new ServerSocket(port)) {
            socket.setReuseAddress(true);
            return true;
        } catch (IOException exception) {
            return false;
        }
    }
    
    //Starts at startingPort and increases by 1 till first available port is found.
    public static int findAvailablePort(int startingPort) {
        int port = startingPort;

        while (!isPortAvailable(port)) {
            port++;
        }

        return port;
    }
}
