import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

public class EchoServer {
    private static final String pathToServerProperties = "Server/res/server_strings.properties";

    public static void main(String args[]) {
        ServerSocket serverSocket;
        try {
            Properties properties = getServerProperty();
            serverSocket = new ServerSocket(Integer.parseInt(properties.getProperty("port")));
        } catch (Exception e) {
            System.err.println("Create server socket: " + e);
            return;
        }
        while (true) try {
            Socket socket = serverSocket.accept();
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);
            String fromClient = br.readLine();
            System.out.println("From client: [" + fromClient + "]");
            pw.println("Echo: " + fromClient);
            socket.close();
        } catch (Exception e) {
            System.err.println("Server exception: " + e);
        }
    }
    private static Properties getServerProperty(){
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream(pathToServerProperties);
            prop.load(input);
            return prop;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}