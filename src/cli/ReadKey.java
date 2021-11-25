package cli;

import java.util.Scanner;

public class ReadKey implements Runnable {

    static Thread thread;

    synchronized public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("1 - start server normally\n" +
                    "2 - activate maintenance mode\n" +
                    "3 - stop server\n" +
                    "4 - exit console\n");

            Scanner scan = new Scanner(System.in);
            int choice = scan.nextInt();
            Main.setServerState(choice);

            if (choice == 4)
                break;
        }
    }
}
