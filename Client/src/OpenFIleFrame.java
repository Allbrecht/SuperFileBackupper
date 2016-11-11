
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.GroupLayout;
import static javax.swing.GroupLayout.DEFAULT_SIZE;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JProgressBar;

public class OpenFIleFrame extends JFrame {
    private JPanel panel;
    private JProgressBar pbar;

    public OpenFIleFrame() {

        initUI(LoadFile());
    }

    private void initUI(File file) {

        pbar = new JProgressBar();
        pbar.setStringPainted(true);

        add(pbar);

        pbar.setValue(50);

        setTitle(file.getName());
        setSize(400, 100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private File LoadFile() {
        JFileChooser fdia = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("Java files",
                "java");
        fdia.addChoosableFileFilter(filter);

        int ret = fdia.showDialog(panel, "Open file");

        if (ret == JFileChooser.APPROVE_OPTION) {
           File file = fdia.getSelectedFile();
            return file;
        }
        return null;
    }

}