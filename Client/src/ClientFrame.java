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

        JButton openFileButton = new JButton(uploadIcon);
        openFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OpenFileDialogOpen();
            }
        });

        JButton restoreButton = new JButton(downloadIcon);
        restoreButton.addActionListener(new ActionListener() {
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

        toolbar.add(openFileButton);
        toolbar.add(restoreButton);
        toolbar.add(exitButton);
        add(toolbar, BorderLayout.NORTH);
    }


    private void OpenFileDialogOpen() {
        OpenFIleFrame openFIlePlease = new OpenFIleFrame();
        openFIlePlease.setVisible(true);
    }
    private void RestoreFileOpen() {
        RestoreFile restoreFile = new RestoreFile();
        restoreFile.setVisible(true);
    }

    private void closeFrame(){
        dispose();
    }
}

