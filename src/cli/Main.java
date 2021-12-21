package cli;

import config.Config;

public class Main{

    public static void main(String[] args) {

        Config config = new Config();
        ReadKey readKey = new ReadKey();

        config.start();
        readKey.start();
    }

}

