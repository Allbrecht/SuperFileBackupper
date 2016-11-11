
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
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class OpenFIleFrame extends JFrame {
    private JPanel panel;

    public OpenFIleFrame() {

        initUI(LoadFile());
    }

    private void initUI(File file) {

        panel = (JPanel) getContentPane();

        setTitle(file.getName());
        setSize(400, 300);
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