package cli;

import java.util.Scanner;

class ReadKey implements Runnable {

    static Thread thread;

    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("1 - start server\n" +
                    "2 - activate/deactivate maintenance mode\n" +
                    "3 - stop server\n" +
                    "4 - exit\n");

            Scanner scan = new Scanner(System.in);
            int choice = scan.nextInt();
            Main.serverState = choice;
            System.out.println(Main.serverState);
            if (choice == 4)
                break;
        }
    }
}
