import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SuperFileBackupperServer extends  Thread{
    private ServerSocket ss;

    public SuperFileBackupperServer(int port) {
        try {
            ss = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            try {
                Socket clientSock = ss.accept();
                saveFile(clientSock);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveFile(Socket clientSock) throws IOException {
        DataInputStream dis = new DataInputStream(clientSock.getInputStream());
        FileOutputStream fos = new FileOutputStream("testfile");
        byte[] buffer = new byte[4096];

        int bytesRead = 0;
        while((bytesRead = dis.read(buffer)) > 0) {
            fos.write(buffer, 0, bytesRead);
        }

        fos.close();
        dis.close();
    }

    public static void main(String[] args) {
        SuperFileBackupperServer fs = new SuperFileBackupperServer(Integer.parseInt(ServerProperties.INSTANCE.getProperty("port")));
        fs.start();
    }

}
