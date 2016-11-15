
import java.awt.*;
import java.io.File;
import javax.swing.GroupLayout;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import static javax.swing.GroupLayout.Alignment.CENTER;

public class OpenFIleFrame extends JFrame {
    private JPanel panel;
    private JProgressBar pbar;
    private File fileToBuckup;

    public OpenFIleFrame() {
        panel = new JPanel();
        loadFile();
    }

    private void initUI(File file) {

        pbar = new JProgressBar();
        pbar.setStringPainted(true);

        createLayout(pbar);

        pbar.setValue(50);

        setTitle(file.getName());
        setSize(400, 100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void createLayout(JComponent... arg) {

        Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
        );

        gl.setVerticalGroup(gl.createParallelGroup(CENTER)
                .addComponent(arg[0])
        );

        pack();
    }

    private void loadFile() {
        JFileChooser fdia = new JFileChooser();

        int ret = fdia.showDialog(panel, "Open file");

        if (ret == JFileChooser.APPROVE_OPTION) {
            fileToBuckup = fdia.getSelectedFile();
            initUI(fileToBuckup);
            Connection connection = new Connection(fileToBuckup);

        }

    }

}