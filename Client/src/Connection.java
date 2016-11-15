import java.io.*;
import java.net.Socket;

public class Connection {
    Socket socket;

    public Connection(File fileToSend) {
        try {
            socket = new Socket("localhost", Integer.parseInt(ClientProperties.INSTANCE.getProperty("port")));
            sendFile(fileToSend);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void sendFile(File fileToSend) {
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(socket.getOutputStream());
            FileInputStream fis = new FileInputStream(fileToSend);
            byte[] buffer = new byte[4096];
            while (fis.read(buffer) > 0) {
                dos.write(buffer);
            }
            fis.close();
            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
