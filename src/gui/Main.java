package gui;

import config.Config;

import javax.swing.*;
import java.awt.*;

public class Main {

    Config config = new Config();
    JButton startServerButton, stopServerButton, maintenanceModeServerButton, checkRootDirectoryButton, checkMaintenanceDirectoryButton;
    TextField serverPortListeningTextField, serverWebRootDirectoryTextField, serverMaintenanceDirectoryTextField;
    Label serverStatusMessage, serverAddressMessage, serverListeningPortMessage;

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
        serverStatusMessage = new Label("Not running");
        infoPanel.add(serverStatusMessage);
        Label serverAddress = new Label("Server Address: ");
        infoPanel.add(serverAddress);
        serverAddressMessage = new Label("Not running");
        infoPanel.add(serverAddressMessage);
        Label serverListeningPort = new Label("Server Listening Port: ");
        infoPanel.add(serverListeningPort);
        serverListeningPortMessage = new Label("Not running");
        infoPanel.add(serverListeningPortMessage);

        // BUTTONS
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createTitledBorder("WebServer Control"));
        buttonPanel.setLayout(new GridLayout(0, 1));
        startServerButton = new JButton("Start Server");
        startServerButton.addActionListener(e -> startServerFromGUI());
        buttonPanel.add(startServerButton);

        stopServerButton = new JButton("Stop Server");
        stopServerButton.addActionListener(e -> stopServerButtonFromGUI());
        stopServerButton.setEnabled(false);
        buttonPanel.add(stopServerButton);

        maintenanceModeServerButton = new JButton("Activate Maintenance Mode");
        maintenanceModeServerButton.addActionListener(e -> maintenanceModeServerButtonFromGUI());
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
        serverPortListeningTextField = new TextField("12345");
        portPanel.add(serverPortListeningTextField);
        configPanel.add(portPanel);
        // ROOT DIRECTORY
        JPanel checkRootDirectoryPanel = new JPanel();
        checkRootDirectoryPanel.setBorder(BorderFactory.createEmptyBorder());
        Label serverWebRootDirectory = new Label("Server Web Root Directory ");
        checkRootDirectoryPanel.add(serverWebRootDirectory);
        serverWebRootDirectoryTextField = new TextField("C:\\AC CTI\\An IV Sem I\\Sem I\\SSC\\vvs-webserver-project\\src\\TestSite\\");
        checkRootDirectoryPanel.add(serverWebRootDirectoryTextField);
        checkRootDirectoryButton = new JButton("Check Path");
        checkRootDirectoryButton.addActionListener(e -> checkRootDirectoryPath());
        checkRootDirectoryPanel.add(checkRootDirectoryButton);

        configPanel.add(checkRootDirectoryPanel);
        // MAINTENANCE DIRECTORY
        JPanel checkMaintenanceDirectoryPanel = new JPanel();
        checkMaintenanceDirectoryPanel.setBorder(BorderFactory.createEmptyBorder());
        Label serverMaintenanceDirectory = new Label("Server Maintenance Directory ");
        checkMaintenanceDirectoryPanel.add(serverMaintenanceDirectory);
        serverMaintenanceDirectoryTextField = new TextField("C:\\AC CTI\\An IV Sem I\\Sem I\\SSC\\vvs-webserver-project\\src\\TestSite\\");
        checkMaintenanceDirectoryPanel.add(serverMaintenanceDirectoryTextField);
        checkMaintenanceDirectoryButton = new JButton("Check Path");
        checkMaintenanceDirectoryButton.addActionListener(e -> checkMaintenanceDirectoryPath());
        maintenanceModeServerButton.setEnabled(false);
        checkMaintenanceDirectoryPanel.add(checkMaintenanceDirectoryButton);

        configPanel.add(checkMaintenanceDirectoryPanel);

        generalPanel.add(configPanel);

        frame.add(generalPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("VVS WebServer");
        frame.pack();
        frame.setVisible(true);
        config.start();
    }

    public void startServerFromGUI(){
        startServerButton.setEnabled(false);
        serverPortListeningTextField.setEnabled(false);
        serverWebRootDirectoryTextField.setEnabled(false);
        stopServerButton.setEnabled(true);
        maintenanceModeServerButton.setEnabled(true);
        int port = Integer.parseInt(serverPortListeningTextField.getText());
        config.setPort(port);
        serverStatusMessage.setText("Running");
        serverAddressMessage.setText("localhost:" + port);
        serverListeningPortMessage.setText(String.valueOf(port));
        Config.serverState = 1;
    }

    public void stopServerButtonFromGUI(){
        stopServerButton.setEnabled(false);
        maintenanceModeServerButton.setEnabled(false);
        maintenanceModeServerButton.setText("Activate Maintenance Mode");
        serverMaintenanceDirectoryTextField.setEnabled(true);
        startServerButton.setEnabled(true);
        serverWebRootDirectoryTextField.setEnabled(true);
        serverPortListeningTextField.setEnabled(true);
        serverStatusMessage.setText("Stopped");
        serverAddressMessage.setText("Stopped");
        serverListeningPortMessage.setText("Stopped");
        Config.serverState = 3;
    }

    public void maintenanceModeServerButtonFromGUI(){
        if(maintenanceModeServerButton.getText().equals("Activate Maintenance Mode")){
            Config.serverState = 2;
            maintenanceModeServerButton.setText("Deactivate Maintenance Mode");
            serverMaintenanceDirectoryTextField.setEnabled(false);
            serverWebRootDirectoryTextField.setEnabled(true);
            serverStatusMessage.setText("Maintenance");
        }else{
            Config.serverState = 1;
            maintenanceModeServerButton.setText("Activate Maintenance Mode");
            serverMaintenanceDirectoryTextField.setEnabled(true);
            serverWebRootDirectoryTextField.setEnabled(false);
            serverStatusMessage.setText("Running");
        }
    }

    public void checkRootDirectoryPath(){
        System.out.println("Works4");
    }

    public void checkMaintenanceDirectoryPath(){
        System.out.println("Works5");
    }
}
