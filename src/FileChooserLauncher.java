import javax.swing.*;
import java.io.File;
import java.nio.file.Path;

/**
 * Allows the creation of objects containing a JFileChooser
 * and basic methods for prompting the user to select a file and
 * clearing the chooser.
 * @author Zoe Aspenns aspennza@mail.uc.edu
 */
public class FileChooserLauncher
{
    //This JFileChooser is used to prompt the user to pick a file
    private JFileChooser chooser;

    //This constructor initializes the chooser and sets its default directory
    public FileChooserLauncher() {
        chooser = new JFileChooser();
        File workingDirectory = new File(System.getProperty("user.dir"));
        chooser.setCurrentDirectory(workingDirectory);
    }

    /**
     * This method prompts the user to choose a file, returning the Path if they pick one and returning null if they don't
     * @return a Path representing the user's chosen file
     */
    public Path chooseFile() {
        if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File selected = chooser.getSelectedFile();
            return selected.toPath();
        }
        return null;
    }

    /**
     * This method initializes a new JFileChooser and resets its directory
     */
    public void resetChooser() {
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
    }

    public JFileChooser getChooser() {
        return chooser;
    }
}
