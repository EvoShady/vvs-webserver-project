package cli;

import config.Config;

public class Main{
    public volatile static int serverState;

    public static void main(String[] args) {

        Config config = new Config();
        ReadKey readKey = new ReadKey();

        config.start();
        readKey.start();
    }

    public static synchronized void setServerState(int serverState){
        Main.serverState = serverState;
    }
}

