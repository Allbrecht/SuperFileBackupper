import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

public class ClientFrame extends JFrame {
    public ClientFrame(String clientFrame) {

        createToolBar();
        setTitle("SuperFileBackupper");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createToolBar() {

        JToolBar toolbar = new JToolBar();

        ImageIcon icon = new ImageIcon("exit.png");

        JButton exitButton = new JButton(icon);
        toolbar.add(exitButton);



        add(toolbar, BorderLayout.NORTH);
    }
}

