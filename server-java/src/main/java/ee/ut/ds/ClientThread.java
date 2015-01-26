package ee.ut.ds;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread implements Runnable {

    private Socket client;

    public ClientThread(Socket client) {
        this.client = client;
    }

    public void run () {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter writer = new PrintWriter(client.getOutputStream(), true);

            String command = reader.readLine();
            System.out.println("Received command:" + command);

            String result = "";
            String line;
            Process child = Runtime.getRuntime().exec(command);
            child.waitFor();
            BufferedReader output = new BufferedReader(new InputStreamReader(child.getInputStream()));
            while ((line = output.readLine()) != null) {
                result = result.concat(line);
                result = result.concat("\n");
            }
            output.close();

            writer.println(result);
        }
        catch (Exception e) {
            System.err.println("Exception caught: client disconnected.");
        }
        finally {
            try {
                client.close();
            } catch (Exception e ) {
                e.printStackTrace();
            }
        }
    }

}