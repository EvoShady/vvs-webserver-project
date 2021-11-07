package cliTests;

import cli.Main;
import cli.ReadKey;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class ReadKeyTest {
    InputStream systemInBackup;
    @Before
    public void setUp(){
        systemInBackup = System.in; // backup System.in to restore it later
    }

    @After
    public void tearDown(){
        System.setIn(systemInBackup);
    }

    @Test
    public void testInput(){
        ReadKey readKey = new ReadKey();

        ByteArrayInputStream in = new ByteArrayInputStream("4".getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        readKey.run();

        assertEquals(4, Main.serverState);
    }
}
