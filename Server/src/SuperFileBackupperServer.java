import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SuperFileBackupperServer extends Thread {
    private ServerSocket ss;

    public SuperFileBackupperServer(int port) {
        try {
            ss = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        System.out.println("server running");

        while (true) {
            Socket clientSock = null;
            try {
                clientSock = ss.accept();
                receiveFile(clientSock);
                sendMessage("przyjąłem plik", clientSock);
            } catch (IOException e) {
                sendMessage("nie udało się przyjąć pliku", clientSock);

            }
        }
    }

    private void sendMessage(String msg, Socket clientSock) {
        System.out.println("Sending message..");

        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(clientSock.getOutputStream());
            dos.writeBytes(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }/* finally {
            try {
                dos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
        System.out.println("message sent");

    }

    private void receiveFile(Socket clientSock) {
        System.out.println("Receiveing message..");

        DataInputStream dis = null;
        FileOutputStream fos = null;
        try {
            dis = new DataInputStream(clientSock.getInputStream());

            long size = dis.readLong();

            fos = new FileOutputStream("testfile\n");
            byte[] buffer = new byte[4096];

            long bytesRead = 0;
            int chunkBuffer;
            while (bytesRead < size) {
                chunkBuffer = dis.read(buffer);
                fos.write(buffer, 0, chunkBuffer);
                bytesRead += chunkBuffer;
            }

            System.out.println("koniec zapisu pliku");
        } catch (IOException e) {
            sendMessage("nie ok", clientSock);
            e.printStackTrace();
        }

        System.out.println("message received");

       /* try {
            fos.close();
            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

    public static void main(String[] args) {
        SuperFileBackupperServer fs = new SuperFileBackupperServer(Integer.parseInt(ServerProperties.INSTANCE.getProperty("port")));
        fs.start();
    }

}
