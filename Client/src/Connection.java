import java.io.*;
import java.net.Socket;

public class Connection {
    private Socket socket;

    public Connection(File fileToSend) {
        try {
            socket = new Socket("localhost", Integer.parseInt(ClientProperties.INSTANCE.getProperty("port")));
            sendFile(fileToSend);
            receiveMsg();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void receiveMsg() {
        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            String msg = dis.readUTF();
            System.out.println(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   /* private void sendFileName(String fileName) {
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(socket.getOutputStream());
            byte[] stringByte = fileName.getBytes();
            dos.write(stringByte);
            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    private void send(byte[] array){

    }

    private void sendFile(File fileToSend) {
        DataOutputStream dos = null;
        FileInputStream fis = null;
        try {
            dos = new DataOutputStream(socket.getOutputStream());
             fis = new FileInputStream(fileToSend);
            byte[] buffer = new byte[4096];
            while (fis.read(buffer) > 0) {
                dos.write(buffer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                fis.close();
                dos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}


