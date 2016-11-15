import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientFrame extends JFrame {

    public ClientFrame(String clientFrame) {

        createToolBar();
        setTitle(clientFrame);
        setSize(450, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void createToolBar() {

        JToolBar toolbar = new JToolBar();

        ImageIcon exitIcon = new ImageIcon("Client/Icons/exit.png");
        ImageIcon downloadIcon = new ImageIcon("Client/Icons/download.png");
        ImageIcon uploadIcon = new ImageIcon("Client/Icons/upload.png");

        JButton OFileButton = new JButton(downloadIcon);
        OFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OpenFileDialogOpen();
            }
        });

        JButton RestoreButton = new JButton(uploadIcon);
        RestoreButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RestoreFileOpen();
            }
        });

        JButton exitButton = new JButton(exitIcon);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                closeFrame();
            }
        });

        toolbar.add(OFileButton);
        toolbar.add(RestoreButton);
        toolbar.add(exitButton);
        add(toolbar, BorderLayout.NORTH);
    }


    private void OpenFileDialogOpen() {
        OpenFIleFrame OFile = new OpenFIleFrame();
        OFile.setVisible(true);
    }
    private void RestoreFileOpen() {
        RestoreFile RFile = new RestoreFile();
        RFile.setVisible(true);
    }

    private void closeFrame(){
        dispose();
    }
}

