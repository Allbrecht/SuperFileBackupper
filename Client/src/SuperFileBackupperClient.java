import java.awt.*;

public class SuperFileBackupperClient {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            public void run() {
                ClientFrame mainWindow =new ClientFrame("Super File Backupper");
                mainWindow.setVisible(true);
            }
        });
    }
}
