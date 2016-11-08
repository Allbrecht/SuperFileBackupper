import java.awt.*;

public class SuperFileBackupperClient {
    public static void main(String[] args) {
        System.out.println(ClientProperties.INSTANCE.getProperty("key"));
        EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ClientFrame("ClientFrame");

            }

        });
    }
}
