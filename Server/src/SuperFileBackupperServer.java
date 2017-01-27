

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 1- ok
 * -1 not ok (exception)
 * 0 bad
 */
public class SuperFileBackupperServer extends Thread {
    private ServerSocket ss;
    private HashManager hashManager;
    private static final String path = "Server/buckedFiles";
    private String name;

    public SuperFileBackupperServer(int port) {
        try {
            ss = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        System.out.println("server running");
        hashManager = new HashManager();

        while (true) {
            Socket clientSock = null;
            try {
                clientSock = ss.accept();
                switch (receiveTask(clientSock)) {
                    case "Save":
                        receiveHash(clientSock);
                        receiveName(clientSock);
                        receiveFile(clientSock);
                        sendMessage(1, clientSock);
                        break;
                    case "Restore":
                        sendFileListAsString(clientSock);
                        receiveName(clientSock);
                        sendFile(clientSock);
                }
            } catch (IOException e) {
                sendMessage(-1, clientSock);

            }
        }
    }

    private void sendFileListAsString(Socket clientSock) {
        OutputStream out = null;
        try {
            out = clientSock.getOutputStream();
            ObjectOutputStream oout = new ObjectOutputStream(out);
            oout.writeObject(FileManager.getFilesAsList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendFile(Socket clientSock) {
        DataOutputStream dos = null;
        FileInputStream fis = null;
        try {
            dos = new DataOutputStream(clientSock.getOutputStream());
            File fileToSend = FileManager.getFile(name);
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
        name = null;
    }

    private String receiveTask(Socket clientSock) {
        ObjectInputStream oin = null;
        try {
            oin = new ObjectInputStream(clientSock.getInputStream());
            return (String) oin.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void receiveName(Socket clientSock) {
        ObjectInputStream oin = null;
        try {
            oin = new ObjectInputStream(clientSock.getInputStream());
            name = (String) oin.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void receiveHash(Socket clientSock) {
        try {
            DataInputStream is = new DataInputStream(clientSock.getInputStream());
            int hash = is.readInt();
            sendMessage(hashManager.isNewHash(hash) ? 1 : 0, clientSock);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(int msg, Socket clientSock) {
        System.out.println("Sending message..");

        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(clientSock.getOutputStream());
            dos.write(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("message sent");

    }

    private void receiveFile(Socket clientSock) {
        DataInputStream dis = null;
        FileOutputStream fos = null;
        try {
            dis = new DataInputStream(clientSock.getInputStream());
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

            hashManager.addHash(fos.hashCode());
            hashManager.saveHashes();
            System.out.println("koniec zapisu pliku");
        } catch (IOException e) {
            sendMessage(-1, clientSock);
            e.printStackTrace();
        }

        System.out.println("message received");
    }

    public static void main(String[] args) {
        SuperFileBackupperServer fs = new SuperFileBackupperServer(Integer.parseInt(ServerProperties.INSTANCE.getProperty("port")));
        fs.start();
    }

}
