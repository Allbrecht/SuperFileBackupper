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
        System.out.println("receive file");
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            String msg = in.readLine();
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
    private void send(byte[] array) {

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
       /* finally {
            try {
                //fis.close();
                //dos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }*/
    }
}


