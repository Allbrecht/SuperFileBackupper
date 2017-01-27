
import java.io.*;
import java.net.Socket;
import java.util.List;

public class FileRestorer {

    private Socket socket;
    private String name;
    private static final String path = "Client/res";


    public FileRestorer() {
        try {
            socket = new Socket("localhost", Integer.parseInt(ClientProperties.INSTANCE.getProperty("port")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getFilesList() {
        sendGetCommand();
        return getResponse();
    }

    public void getFile() {
        DataInputStream dis = null;
        FileOutputStream fos = null;
        try {
            dis = new DataInputStream(socket.getInputStream());
            fos = new FileOutputStream(path + "/" + name);
            long size = dis.readLong();

            byte[] buffer = new byte[4096];

            long bytesRead = 0;
            int chunkBuffer;
            while (bytesRead < size) {
                chunkBuffer = dis.read(buffer);
                fos.write(buffer, 0, chunkBuffer);
                bytesRead += chunkBuffer;
            }

            socket.close();
            System.out.println("koniec zapisu pliku");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void sendGetCommand() {
        ObjectOutputStream oin = null;
        try {
            oin = new ObjectOutputStream(socket.getOutputStream());
            oin.writeObject("Restore");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> getResponse() {
        InputStream in = null;
        try {
            in = socket.getInputStream();
            ObjectInputStream iin = new ObjectInputStream(in);
            return (List<String>) iin.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveName(String name) {
        this.name = name;
    }
}