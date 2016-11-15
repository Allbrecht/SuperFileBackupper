import java.io.*;
import java.net.Socket;

public class Connection {
    Socket socket;

    public Connection(File fileToSend) {
        try {
            socket = new Socket("localhost", Integer.parseInt(ClientProperties.INSTANCE.getProperty("port")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        sendFile(fileToSend);
    }

    private void sendFile(File fileToSend) {
        long length = fileToSend.length();
        byte[] bytes = new byte[16 * 1024];
        InputStream in = null;
        try {
            in = new FileInputStream(fileToSend);
            OutputStream out = socket.getOutputStream();
            int count;
            while ((count = in.read(bytes)) > 0) {
                out.write(bytes, 0, count);
            }
            out.close();
            in.close();
            socket.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
