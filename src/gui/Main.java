package gui;

import javax.swing.*;
import java.awt.*;

public class Main {

    public Main(){
        JFrame frame = new JFrame();

        JPanel generalPanel = new JPanel();
        generalPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        generalPanel.setLayout(new GridLayout(0, 1));

        // INFO
        JPanel infoPanel = new JPanel();
        infoPanel.setBorder(BorderFactory.createTitledBorder("WebServer Info"));
        infoPanel.setLayout(new GridLayout(3, 2));
        Label serverStatus = new Label("Server Status: ");
        infoPanel.add(serverStatus);
        Label serverStatusMessage = new Label("Not running");
        infoPanel.add(serverStatusMessage);
        Label serverAddress = new Label("Server Address: ");
        infoPanel.add(serverAddress);
        Label serverAddressMessage = new Label("Not running");
        infoPanel.add(serverAddressMessage);
        Label serverListeningPort = new Label("Server Listening Port: ");
        infoPanel.add(serverListeningPort);
        Label serverListeningPortMessage = new Label("Not running");
        infoPanel.add(serverListeningPortMessage);

        // BUTTONS
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createTitledBorder("WebServer Control"));
        buttonPanel.setLayout(new GridLayout(0, 1));
        JButton startServerButton = new JButton("Start Server");
        buttonPanel.add(startServerButton);
        JButton stopServerButton = new JButton("Stop Server");
        buttonPanel.add(stopServerButton);
        JButton maintenanceModeServerButton = new JButton("Activate Maintenance Mode");
        buttonPanel.add(maintenanceModeServerButton);

        // INFO AND BUTTON PANEL
        JPanel infoAndButtonPanel = new JPanel();
        infoAndButtonPanel.setBorder(BorderFactory.createEmptyBorder());
        infoAndButtonPanel.add(infoPanel);
        infoAndButtonPanel.add(buttonPanel);
        generalPanel.add(infoAndButtonPanel);

        // CONFIG
        JPanel configPanel = new JPanel();
        configPanel.setBorder(BorderFactory.createTitledBorder("WebServer Configuration"));
        configPanel.setLayout(new GridLayout(3, 2));
        Label serverPortListening = new Label("Server Listening On Port ");
        configPanel.add(serverPortListening);
        TextField serverPortListeningTextField = new TextField("12345");
        configPanel.add(serverPortListeningTextField);
        Label serverWebRootDirectory = new Label("Server Web Root Directory ");
        configPanel.add(serverWebRootDirectory);
        TextField serverWebRootDirectoryTextField = new TextField("yes");
        configPanel.add(serverWebRootDirectoryTextField);
        Label serverMaintenanceDirectory = new Label("Server Maintenance Directory ");
        configPanel.add(serverMaintenanceDirectory);
        TextField serverMaintenanceDirectoryTextField = new TextField("yes");
        configPanel.add(serverMaintenanceDirectoryTextField);
        generalPanel.add(configPanel);

        frame.add(generalPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("VVS WebServer");
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }

}
