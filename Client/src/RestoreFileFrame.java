import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class RestoreFileFrame extends JFrame {

    FileRestorer fileRestorer;
    JList jList;

    public RestoreFileFrame() {
        initUI();

        showList();
    }

    private void showList() {
        fileRestorer = new FileRestorer();
        jList = new JList(fileRestorer.getFilesList().toArray());
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(jList));

        jList.addListSelectionListener(
                new ListSelectionListener() {

                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        fileRestorer.saveName((String) jList.getSelectedValue());
                        fileRestorer.getFile();
                        setVisible(false);
                        dispose();
                    }
                }
        );
    }

    private void initUI() {

        setTitle("Restore File");
        setSize(300, 400);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
