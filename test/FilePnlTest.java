import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class FilePnlTest {

    FilePnl pnl;
    TagAnalyzer analyzer;

    @BeforeEach
    void setUp()
    {
        analyzer = new TagAnalyzer();
        pnl = new FilePnl(analyzer);
    }

    @Test
    void testConstructor()
    {
        assertNotNull(pnl.getAnalyzer());
        assertTrue(pnl.getLayout() instanceof GridBagLayout);
        assertNotNull(pnl.getFileLbl());
        assertNotNull(pnl.getFileTF());
        assertNotNull(pnl.getSelectBtn());
        assertEquals("Chosen File:", pnl.getFileLbl().getText());
        assertFalse(pnl.getFileTF().isEditable());
        assertEquals("Choose a file", pnl.getSelectBtn().getText());
        assertEquals(3, pnl.getComponentCount());
    }
}