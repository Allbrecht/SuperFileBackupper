


import sun.misc.IOUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


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
                receiveHash(clientSock);
                receiveName(clientSock);
                receiveFile(clientSock);
                sendMessage(1, clientSock);
            } catch (IOException e) {
                sendMessage(-1, clientSock);

            }
        }
    }

    private void receiveName(Socket clientSock) {

       /* try {
            //BufferedInputStream is = new BufferedInputStream(clientSock.getInputStream());
            byte [] bytes = IOUtils.readFully(clientSock.getInputStream(),8,true);
            name =  new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
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
            fos = new FileOutputStream(path+"/"+name);
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
