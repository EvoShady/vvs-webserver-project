package gui;

import config.Config;
import webserver.WebServer;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Main {

    Config config = new Config();
    JButton startServerButton, stopServerButton, maintenanceModeServerButton, checkRootDirectoryButton, checkMaintenanceDirectoryButton;
    TextField serverPortListeningTextField, serverWebRootDirectoryTextField, serverMaintenanceDirectoryTextField;
    Label serverStatusMessage, serverAddressMessage, serverListeningPortMessage;
    JFrame frame = new JFrame();

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        initializeUI();
    }

    public void initializeUI() {

        // INFO
        Label serverStatus = new Label("Server Status: ");
        serverStatusMessage = new Label("Not running");
        Label serverAddress = new Label("Server Address: ");
        serverAddressMessage = new Label("Not running");
        Label serverListeningPort = new Label("Server Listening Port: ");
        serverListeningPortMessage = new Label("Not running");
        JPanel infoPanel = new JPanel();
        infoPanel.setBorder(BorderFactory.createTitledBorder("WebServer Info"));
        infoPanel.setLayout(new GridLayout(3, 2));
        infoPanel.add(serverStatus);
        infoPanel.add(serverStatusMessage);
        infoPanel.add(serverAddress);
        infoPanel.add(serverAddressMessage);
        infoPanel.add(serverListeningPort);
        infoPanel.add(serverListeningPortMessage);

        // BUTTONS
        startServerButton = new JButton("Start Server");
        startServerButton.addActionListener(e -> startServerFromGUI());
        stopServerButton = new JButton("Stop Server");
        stopServerButton.addActionListener(e -> stopServerButtonFromGUI());
        stopServerButton.setEnabled(false);
        maintenanceModeServerButton = new JButton("Activate Maintenance Mode");
        maintenanceModeServerButton.addActionListener(e -> maintenanceModeServerButtonFromGUI());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createTitledBorder("WebServer Control"));
        buttonPanel.setLayout(new GridLayout(0, 1));
        buttonPanel.add(startServerButton);
        buttonPanel.add(stopServerButton);
        buttonPanel.add(maintenanceModeServerButton);

        // INFO AND BUTTON PANEL
        JPanel infoAndButtonPanel = new JPanel();
        infoAndButtonPanel.setBorder(BorderFactory.createEmptyBorder());
        infoAndButtonPanel.add(infoPanel);
        infoAndButtonPanel.add(buttonPanel);

        // CONFIG
        // PORT
        Label serverPortListening = new Label("Server Listening On Port ");
        serverPortListeningTextField = new TextField("12345");
        JPanel portPanel = new JPanel();
        portPanel.setBorder(BorderFactory.createEmptyBorder());
        portPanel.add(serverPortListening);
        portPanel.add(serverPortListeningTextField);
        // ROOT DIRECTORY
        Label serverWebRootDirectory = new Label("Server Web Root Directory ");
        serverWebRootDirectoryTextField = new TextField("C:\\AC CTI\\An IV Sem I\\Sem I\\SSC\\vvs-webserver-project\\src\\TestSite\\");
        checkRootDirectoryButton = new JButton("Check Path");
        checkRootDirectoryButton.addActionListener(e -> checkRootDirectoryPath());
        JPanel checkRootDirectoryPanel = new JPanel();
        checkRootDirectoryPanel.setBorder(BorderFactory.createEmptyBorder());
        checkRootDirectoryPanel.add(serverWebRootDirectory);
        checkRootDirectoryPanel.add(serverWebRootDirectoryTextField);
        checkRootDirectoryPanel.add(checkRootDirectoryButton);
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
        JPanel configPanel = new JPanel();
        configPanel.setBorder(BorderFactory.createTitledBorder("WebServer Configuration"));
        configPanel.setLayout(new GridLayout(0, 1));
        configPanel.add(portPanel);
        configPanel.add(checkRootDirectoryPanel);
        configPanel.add(checkMaintenanceDirectoryPanel);

        //MAIN FRAME

        JPanel generalPanel = new JPanel();
        generalPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        generalPanel.setLayout(new GridLayout(0, 1));

        generalPanel.add(infoAndButtonPanel);
        generalPanel.add(configPanel);

        frame.add(generalPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("VVS WebServer");
        frame.pack();
        frame.setVisible(true);
        config.start();
    }

    public void startServerFromGUI() {
        boolean isValidPath = isValidRootPathAndFile();
        if (isValidPath) {
            WebServer.ROOT_DIRECTORY = serverWebRootDirectoryTextField.getText();
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
        } else checkDirectoryPathPopup(false, WebServer.DEFAULT_FILE);
    }

    public void stopServerButtonFromGUI() {
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

    public void maintenanceModeServerButtonFromGUI() {
        boolean isValidPath;
        if (maintenanceModeServerButton.getText().equals("Activate Maintenance Mode")) {
            isValidPath = isValidMaintenancePathAndFile();
            if (isValidPath) {
                WebServer.MAINTENANCE_ROOT_DIRECTORY = serverMaintenanceDirectoryTextField.getText();
                Config.serverState = 2;
                maintenanceModeServerButton.setText("Deactivate Maintenance Mode");
                serverMaintenanceDirectoryTextField.setEnabled(false);
                serverWebRootDirectoryTextField.setEnabled(true);
                serverStatusMessage.setText("Maintenance");
            } else {
                checkDirectoryPathPopup(false, "maintenance.html");
            }
        } else {
            isValidPath = isValidRootPathAndFile();
            if (isValidPath) {
                WebServer.ROOT_DIRECTORY = serverWebRootDirectoryTextField.getText();
                Config.serverState = 1;
                maintenanceModeServerButton.setText("Activate Maintenance Mode");
                serverMaintenanceDirectoryTextField.setEnabled(true);
                serverWebRootDirectoryTextField.setEnabled(false);
                serverStatusMessage.setText("Running");
            } else {
                checkDirectoryPathPopup(false, WebServer.DEFAULT_FILE);
            }
        }
    }

    public void checkRootDirectoryPath() {
        boolean isValid = isValidRootPathAndFile();
        checkDirectoryPathPopup(isValid, WebServer.DEFAULT_FILE);
    }

    public void checkMaintenanceDirectoryPath() {
        boolean isValid = isValidMaintenancePathAndFile();
        checkDirectoryPathPopup(isValid, WebServer.MAINTENANCE_FILE);
    }

    public boolean isValidRootPathAndFile() {
        File f = new File(serverWebRootDirectoryTextField.getText() + WebServer.DEFAULT_FILE);
        return f.canRead();
    }

    public boolean isValidMaintenancePathAndFile() {
        File f = new File(serverMaintenanceDirectoryTextField.getText() + WebServer.MAINTENANCE_FILE);
        return f.canRead();
    }

    public void checkDirectoryPathPopup(boolean isValid, String fileCompleteName) {
        if (fileCompleteName.equals(WebServer.DEFAULT_FILE)) {
            if (isValid) {
                JOptionPane.showMessageDialog(frame, serverWebRootDirectoryTextField.getText() + WebServer.DEFAULT_FILE + "\nIS A VALID PATH.");
            } else {
                JOptionPane.showMessageDialog(frame, serverWebRootDirectoryTextField.getText() + WebServer.DEFAULT_FILE + "\nIS NOT A VALID PATH.");
            }
        } else if (fileCompleteName.equals(WebServer.MAINTENANCE_FILE)) {
            if (isValid) {
                JOptionPane.showMessageDialog(frame, serverMaintenanceDirectoryTextField.getText() + WebServer.MAINTENANCE_FILE + "\nIS A VALID PATH.");
            } else {
                JOptionPane.showMessageDialog(frame, serverMaintenanceDirectoryTextField.getText() + WebServer.MAINTENANCE_FILE + "\nIS NOT A VALID PATH.");
            }
        }
    }
}
