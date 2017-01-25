import java.io.*;
import java.net.Socket;

public class Connection {
    private Socket socket;

    public Connection(File fileToSend) {
        try {
            socket = new Socket("localhost", Integer.parseInt(ClientProperties.INSTANCE.getProperty("port")));
            sendHash(fileToSend);
            if (receiveHashResponse()) {
                sendName(fileToSend);
                sendFile(fileToSend);
                receiveMsg();
            } else {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void sendName(File fileToSend) {
        OutputStream out = null;
        try {
            out = socket.getOutputStream();
            ObjectOutputStream oout = new ObjectOutputStream(out);
            oout.writeObject(fileToSend.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private boolean receiveHashResponse() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            int response = in.read();
            return response == 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private void sendHash(File fileToSend) {
        int hash = fileToSend.hashCode();
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(socket.getOutputStream());
            dos.writeInt(hash);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void receiveMsg() {

        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            int msg = in.read();
            System.out.println("is file saved? = " + msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendFile(File fileToSend) {
        DataOutputStream dos = null;
        FileInputStream fis = null;
        try {
            dos = new DataOutputStream(socket.getOutputStream());

            long size = fileToSend.length();
            dos.writeLong(size);

            fis = new FileInputStream(fileToSend);
            byte[] buffer = new byte[4096];
            while (fis.read(buffer) > 0) {
                dos.write(buffer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


