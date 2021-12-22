package gui;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        new Main();
    }

    public Main(){
        initializeUI();
    }

    public void initializeUI(){
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
        configPanel.setLayout(new GridLayout(0, 1));
        // PORT
        JPanel portPanel = new JPanel();
        portPanel.setBorder(BorderFactory.createEmptyBorder());
        Label serverPortListening = new Label("Server Listening On Port ");
        portPanel.add(serverPortListening);
        TextField serverPortListeningTextField = new TextField("12345");
        portPanel.add(serverPortListeningTextField);
        configPanel.add(portPanel);
        // ROOT DIRECTORY
        JPanel checkRootDirectoryPanel = new JPanel();
        checkRootDirectoryPanel.setBorder(BorderFactory.createEmptyBorder());
        Label serverWebRootDirectory = new Label("Server Web Root Directory ");
        checkRootDirectoryPanel.add(serverWebRootDirectory);
        TextField serverWebRootDirectoryTextField = new TextField("C:\\AC CTI\\An IV Sem I\\Sem I\\SSC\\vvs-webserver-project\\src\\TestSite\\");
        checkRootDirectoryPanel.add(serverWebRootDirectoryTextField);
        JButton checkRootDirectoryButton = new JButton("Check Path");
        checkRootDirectoryPanel.add(checkRootDirectoryButton);
        configPanel.add(checkRootDirectoryPanel);
        // MAINTENANCE DIRECTORY
        JPanel checkMaintenanceDirectoryPanel = new JPanel();
        checkMaintenanceDirectoryPanel.setBorder(BorderFactory.createEmptyBorder());
        Label serverMaintenanceDirectory = new Label("Server Maintenance Directory ");
        checkMaintenanceDirectoryPanel.add(serverMaintenanceDirectory);
        TextField serverMaintenanceDirectoryTextField = new TextField("C:\\AC CTI\\An IV Sem I\\Sem I\\SSC\\vvs-webserver-project\\src\\TestSite\\");
        checkMaintenanceDirectoryPanel.add(serverMaintenanceDirectoryTextField);
        JButton checkMaintenanceDirectoryButton = new JButton("Check Path");
        checkMaintenanceDirectoryPanel.add(checkMaintenanceDirectoryButton);
        configPanel.add(checkMaintenanceDirectoryPanel);

        generalPanel.add(configPanel);

        frame.add(generalPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("VVS WebServer");
        frame.pack();
        frame.setVisible(true);
    }
}
