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
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(clientSock.getOutputStream());
            dos.writeBytes(msg);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                dos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void receiveFile(Socket clientSock) {
        DataInputStream dis = null;
        FileOutputStream fos = null;
        try {
            dis = new DataInputStream(clientSock.getInputStream());
            fos = new FileOutputStream("testfile");
            byte[] buffer = new byte[4096];

            int bytesRead = 0;
            while ((bytesRead = dis.read(buffer)) > 0) {
                fos.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            sendMessage("nie ok", clientSock);
            e.printStackTrace();
        }


        try {
            fos.close();
            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        SuperFileBackupperServer fs = new SuperFileBackupperServer(Integer.parseInt(ServerProperties.INSTANCE.getProperty("port")));
        fs.start();
    }

}
