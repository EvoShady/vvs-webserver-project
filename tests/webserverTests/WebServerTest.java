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
        String s = "test.html";
        assertEquals(webServer.getROOT_DIRECTORY() + s, webServer.determineServerServingPage(s));
    }

    @Test
    public void worksWithNotExtensionTerminatedString(){
        String s = "test2";
        String ext = ".html";
        assertEquals(webServer.getROOT_DIRECTORY() + s + ext, webServer.determineServerServingPage(s));
    }

    @Test
    public void worksWithJpgs(){
        String s = "test3.jpg";
        assertEquals(webServer.getROOT_DIRECTORY() + s, webServer.determineServerServingPage(s));
    }

    @Test
    public void worksInMaintenanceMode(){
        webServer.setMaintenanceMode(true);
        assertEquals(webServer.getMAINTENANCE_ROOT_DIRECTORY() + WebServer.MAINTENANCE_FILE, webServer.determineServerServingPage("test4.jpg"));
    }

    @Test
    public void worksInDefaultMode(){
        assertEquals(webServer.getROOT_DIRECTORY() + WebServer.DEFAULT_FILE, webServer.determineServerServingPage("/"));
    }

    @Test (expected = NullPointerException.class)
    public void runServerWithNothing(){
        webServer.run();
    }
}
