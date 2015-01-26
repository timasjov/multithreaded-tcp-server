package ee.ut.ds;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class TCPServer {

    public TCPServer() { }

    public static void main(String args[]) {
        TCPServer server = new TCPServer();
        server.communicate();
    }

    private void communicate() {
        final int port = 6789;

        AtomicInteger numThreads = new AtomicInteger(0);

        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Running on port " + port);

            while (true) {
                Socket clientSocket = server.accept();
                System.out.println("Got connection!");

                Thread clientThread = new Thread(new ClientThread(clientSocket));
                clientThread.start();
                numThreads.incrementAndGet();
                System.out.println("Thread " + numThreads.get() + " started!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}