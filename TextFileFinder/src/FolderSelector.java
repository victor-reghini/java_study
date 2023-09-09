import javax.swing.*;
import javax.swing.border.TitledBorder;

public class FolderSelector extends JFileChooser {
    JFileChooser folder_selector = new JFileChooser();

    FolderSelector(){
        folder_selector.setCurrentDirectory(new java.io.File("."));
        folder_selector.setDialogTitle("Choose the Directory");
        folder_selector.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        folder_selector.setAcceptAllFileFilterUsed(false);
        folder_selector.setBorder(BorderFactory.createTitledBorder(null,"Folder where the search begins", TitledBorder.LEFT, TitledBorder.ABOVE_TOP));
        folder_selector.setDialogType(JFileChooser.OPEN_DIALOG);
        folder_selector.setBounds(50,100,300,150);
    }
}
