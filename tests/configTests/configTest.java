package configTests;

import cli.Main;
import config.Config;
import org.junit.*;

import java.io.IOException;
import java.net.ServerSocket;

import static org.junit.Assert.assertEquals;

public class configTest {
    Config config;

    @Before
    public void setUp() {
        config = new Config();
    }


    @Test
    public void checkChoice1Works(){
        Main.serverState = 1;
        config.start();
    }

    @Test
    public void checkChoice2Works(){
        Main.serverState = 2;
        config.start();
    }

    @Test
    public void checkChoice3Works(){
        Main.serverState = 3;
        config.start();
    }

    @Test
    public void checkChoice4Works(){
        Main.serverState = 4;
        config.run();
        config.start();
    }

    @Test(expected = NullPointerException.class)
    public void checkChoice3DoesNotWork(){
        Main.serverState = 3;
        config.run();
        config.start();
    }

    @Test(expected = NullPointerException.class)
    public void checkStopServer(){
        Main.serverState = 3;
        config.stopServer();
    }

    @Test
    public void checkStopServerWorking() throws IOException {
        Main.serverState = 3;
        config.setServerSocket(new ServerSocket(12343));
        config.stopServer();
    }

    @Test
    public void checkSetServerSocket() throws IOException {
        Main.serverState = 4;
        config.setServerSocket(new ServerSocket(12346));
        assertEquals(12346, config.getServerSocket().getLocalPort());
    }

    @Test
    public void checkStartServerWithNo1() {
        config.startServer();
    }

    @Test
    public void checkMaintainWithNo2() {
        config.activateMaintenanceMode();
    }

}
