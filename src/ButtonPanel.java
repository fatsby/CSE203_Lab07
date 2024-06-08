import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class ButtonPanel extends JPanel {
    private Store store = Store.getInstance();
    public ButtonPanel() {
        init();
    }
    public void init(){
        JButton newCD = new JButton("New CD");
        JButton backupBTN = new JButton("Backup");
        JButton restoreBTN = new JButton("Restore");
        JButton refreshBTN = new JButton("Refresh");
        JButton deleteBTN = new JButton("Delete");
        setLayout(new FlowLayout());
        add(newCD);
        add(backupBTN);
        add(restoreBTN);
        add(refreshBTN);
        add(deleteBTN);

        newCD.addActionListener(e -> {
            NewCDDialog newCDDialog = new NewCDDialog();
            newCDDialog.setVisible(true);
        });

        backupBTN.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int result = fileChooser.showSaveDialog(null);

            // Check if the user selected a directory or cancelled the operation
            if (result == JFileChooser.APPROVE_OPTION) {
                String dir = fileChooser.getSelectedFile().getAbsolutePath();
                FileUtils.write(dir, "CD.eiu", store.getCDlist());
                JOptionPane.showMessageDialog(null, "Successfully backup CDLIST");
            } else {
                JOptionPane.showMessageDialog(null, "Backup operation was cancelled.");
            }
        });

        restoreBTN.addActionListener(e -> {
            FileNameExtensionFilter filter = new FileNameExtensionFilter("CD Files", "eiu");
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(filter);
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String dir = selectedFile.getParent();
                String name = selectedFile.getName();
                ArrayList<CD> cdList = new ArrayList<>(FileUtils.read(dir, name));
                store.setCDlist(cdList);
                FileUtils.write("C:\\CDStore", "CD.eiu", store.getCDlist());
                Main.cdTable.fillTable(store.getCDlist());
                JOptionPane.showMessageDialog(null, "Successfully restored CDLIST");
            } else {
                JOptionPane.showMessageDialog(null, "Restore operation was cancelled.");
            }
        });

        refreshBTN.addActionListener(e -> {
            Main.cdTable.fillTable(store.getCDlist());
        });

        deleteBTN.addActionListener(e -> {
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this CD?", "Warning", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                Main.cdTable.deleteRow();
            }
        });
    }
}
