import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import javax.print.attribute.standard.JobMessageFromOperator;
import javax.swing.*;
import java.awt.*;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TagAnalyzerTest {

    TagAnalyzer analyzer;

    @BeforeEach
    void setUp() {
        analyzer = new TagAnalyzer();
    }

    @Test
    void start() {
        analyzer.start();
        analyzer.getFrame().setVisible(false);
        assertNotNull(analyzer.getWordFreq());
        assertNotNull(analyzer.getCleaner());
        assertNotNull(analyzer.getSaver());
        assertNotNull(analyzer.getChooser1());
        assertNotNull(analyzer.getChooser2());
    }

    @Test
    void generateFrame()
    {
        analyzer.generateFrame();
        analyzer.getFrame().setVisible(false);
        assertNotNull(analyzer.getFrame());
        assertNotNull(analyzer.getTitlePnl());
        assertNotNull(analyzer.getFilePnl());
        assertNotNull(analyzer.getTagPnl());
        assertNotNull(analyzer.getControlPnl());
        assertEquals(JFrame.EXIT_ON_CLOSE, analyzer.getFrame().getDefaultCloseOperation());
        assertEquals("Tag Extractor", analyzer.getFrame().getTitle());
    }

    @Test
    void resetProgram()
    {
        analyzer.start();
        analyzer.getFrame().setVisible(false);
        JFileChooser chooser1 = analyzer.getChooser1().getChooser();
        JFileChooser chooser2 = analyzer.getChooser2().getChooser();
        analyzer.getFilePnl().setFileTF(new JTextField("text"));
        analyzer.getFilePnl().getSelectBtn().setEnabled(false);
        analyzer.getTagPnl().setTagTA(new JTextArea("text"));
        analyzer.setWordFreq(Map.of("cat", 2, "dog", 3));
        analyzer.resetProgram();
        assertNotSame(chooser1, analyzer.getChooser1().getChooser());
        assertNotSame(chooser2, analyzer.getChooser2().getChooser());
        assertEquals("", analyzer.getFilePnl().getFileTF().getText());
        assertTrue(analyzer.getFilePnl().getSelectBtn().isEnabled());
        assertEquals("", analyzer.getTagPnl().getTagTA().getText());
        assertTrue(analyzer.getWordFreq().isEmpty());
    }
}