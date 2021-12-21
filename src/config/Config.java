package config;

import webserver.WebServer;

import java.io.IOException;
import java.net.ServerSocket;

public class Config implements Runnable {
    public volatile static int serverState;

    public static synchronized void setServerState(int serverState){
        Config.serverState = serverState;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    ServerSocket serverSocket = null;

    public void startServer(){
        try {
            serverSocket = new ServerSocket(12345);
            System.out.println("Connection Socket Created");
            try {
                while (serverState == 1) {
                    new WebServer(serverSocket.accept(), false);
                }
            } catch (IOException e) {
                System.err.println("Accept failed.");
                throw new RuntimeException();
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port: 12345.");
            throw new RuntimeException();
        } finally {
            try {
                serverSocket.close();
                System.out.println("Server Closed");
            } catch (IOException e) {
                System.err.println("Could not close port: 12345.");
                throw new RuntimeException();
            }
        }
    }

    public void activateMaintenanceMode(){
        try {
            serverSocket = new ServerSocket(12345);
            System.out.println("Connection Socket Created");
            try {
                while (serverState == 2) {
                    new WebServer(serverSocket.accept(), true);
                }
            } catch (IOException e) {
                System.err.println("Accept failed.");
                throw new RuntimeException();
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port: 12345.");
            throw new RuntimeException();
        }
        finally {
            try {
                serverSocket.close();
                System.out.println("Server Closed");
            } catch (IOException e) {
                System.err.println("Could not close port: 12345.");
                throw new RuntimeException();
            }
        }
    }

    public void stopServer(){
        try {
            serverSocket.close();
        } catch (IOException e) {
            System.err.println("Could not close port: 12345.");
            throw new RuntimeException();
        }
    }

    @Override
    public void run() {
        while(true){
            if (serverState == 1){
                startServer();
            }
            if (serverState == 2){
                activateMaintenanceMode();
            }
            if(serverState == 3){
                stopServer();
            }
            if(serverState == 4){
                break;
            }
        }
    }

    static Thread thread;

    synchronized public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }
}
