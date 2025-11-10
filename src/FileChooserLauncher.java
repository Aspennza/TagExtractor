import javax.swing.*;
import java.io.File;
import java.nio.file.Path;

public class FileChooserLauncher
{
    private JFileChooser chooser;

    public FileChooserLauncher() {
        chooser = new JFileChooser();
        File workingDirectory = new File(System.getProperty("user.dir"));
        chooser.setCurrentDirectory(workingDirectory);
    }

    public Path chooseFile() {
        if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File selected = chooser.getSelectedFile();
            return selected.toPath();
        }
        return null;
    }

    public void resetChooser() {
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
    }

    public JFileChooser getChooser() {
        return chooser;
    }
}
