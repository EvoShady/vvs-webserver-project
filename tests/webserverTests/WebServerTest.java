package webserverTests;

import cli.Main;
import config.Config;
import org.junit.Before;
import org.junit.Test;
import webserver.WebServer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.Assert.assertEquals;

public class WebServerTest {
    WebServer webServer;

    @Before
    public void setUp() {
        webServer = new WebServer();
    }

    @Test
    public void checkCheckMaintenanceModeIsOn(){
        webServer.setMaintenanceMode(true);
        assertEquals(true, webServer.isMaintenanceMode());
    }

    @Test
    public void checkCheckMaintenanceModeIsOff(){
        webServer.setMaintenanceMode(false);
        assertEquals(false, webServer.isMaintenanceMode());
    }

    @Test (expected = FileNotFoundException.class)
    public void checkFile() throws IOException {
        PrintWriter out = null;
        OutputStream dataOut = null;
        String fileRequested = "thisFileDoesNotExist";
        webServer.fileNotFound(out, dataOut, fileRequested);
    }

    @Test
    public void worksWithHtmlTerminatedString(){
        assertEquals("test.html", webServer.determineServerServingPage("test.html"));
    }

    @Test
    public void worksWithNotExtensionTerminatedString(){
        assertEquals("test2.html", webServer.determineServerServingPage("test2"));
    }

    @Test
    public void worksWithJpgs(){
        assertEquals("test3.jpg", webServer.determineServerServingPage("test3.jpg"));
    }

    @Test
    public void worksInMaintenanceMode(){
        webServer.setMaintenanceMode(true);
        assertEquals("/maintenance.html", webServer.determineServerServingPage("test4.jpg"));
    }

    @Test
    public void worksInDefaultMode(){
        assertEquals("/index.html", webServer.determineServerServingPage("/"));
    }

    @Test (expected = NullPointerException.class)
    public void runServerWithNothing(){
        webServer.run();
    }
}
