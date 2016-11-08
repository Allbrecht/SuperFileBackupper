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
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createToolBar() {

        JToolBar toolbar = new JToolBar();

        ImageIcon icon = new ImageIcon("exit.png");

        JButton OFileButton = new JButton(icon);
        OFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                ClientOFileDialog OFileDial=new ClientOFileDialog(this);
                OFileDial.setVisible(true);
            }
        });

        JButton exitButton = new JButton(icon);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                closeFrame();
            }
        });
        toolbar.add(exitButton);

        add(toolbar, BorderLayout.NORTH);
    }
    private void closeFrame(){
        dispose();
    }
}

