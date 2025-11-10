import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class FileChooserLauncherTest {

    FileChooserLauncher chooser;

    @BeforeEach
    void setUp() {
        chooser = new FileChooserLauncher();
    }

    @Test
    void testConstructor()
    {
        assertNotNull(chooser.getChooser());
        assertTrue(chooser.getChooser() instanceof JFileChooser);
    }

    @Test
    void resetChooser()
    {
        JFileChooser before = chooser.getChooser();
        chooser.resetChooser();
        JFileChooser after = chooser.getChooser();

        assertNotSame(before, after);
        assertNotNull(after);
    }
}