package organiser;

import java.io.File;
import javax.swing.JFileChooser;

public class FileChooser {

    private final JFileChooser fileChooser;
    private final File[] files;

    public FileChooser(File currentDirectory) {
        fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setDragEnabled(true);
        fileChooser.setCurrentDirectory(currentDirectory);
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            files = fileChooser.getSelectedFiles();
            currentDirectory = fileChooser.getCurrentDirectory();
        }
        else {
            files = null;
        }

    }

    public File[] getFiles() {
        return files;
    }
}
