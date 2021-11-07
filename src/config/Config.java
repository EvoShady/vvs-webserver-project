package config;

import cli.Main;
import webserver.WebServer;

import java.io.IOException;
import java.net.ServerSocket;

public class Config implements Runnable {
    ServerSocket serverSocket = null;

    public void startServer(){
        try {
            serverSocket = new ServerSocket(12345);
            System.out.println("Connection Socket Created");
            try {
                while (Main.serverState == 1) {
                    new WebServer(serverSocket.accept(), false);
                }
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port: 12345.");
            System.exit(1);
        } finally {
            try {
                serverSocket.close();
                System.out.println("Server Closed");
            } catch (IOException e) {
                System.err.println("Could not close port: 12345.");
                System.exit(1);
            }
        }
    }

    public void activateOrDeactivateMaintenanceMode(){
        try {
            serverSocket = new ServerSocket(12345);
            System.out.println("Connection Socket Created");
            try {
                while (Main.serverState == 2) {
                    new WebServer(serverSocket.accept(), true);
                }
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port: 12345.");
            System.exit(1);
        }
        finally {
            try {
                serverSocket.close();
                System.out.println("Server Closed");
            } catch (IOException e) {
                System.err.println("Could not close port: 12345.");
                System.exit(1);
            }
        }
    }

    public void stopServer(){
        try {
            serverSocket.close();
        } catch (IOException e) {
            System.err.println("Could not close port: 12345.");
            System.exit(1);
        }
    }

    @Override
    public void run() {
        while(true){
            if (Main.serverState == 1){
                startServer();
            }
            if (Main.serverState == 2){
                activateOrDeactivateMaintenanceMode();
            }
            if(Main.serverState == 3){
                stopServer();
            }
        }
    }

    static Thread thread;

    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }
}
