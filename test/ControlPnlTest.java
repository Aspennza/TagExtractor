import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ControlPnlTest {

    ControlPnl pnl;
    TagAnalyzer analyzer;

    @BeforeEach
    void setUp()
    {
        analyzer = new TagAnalyzer();
        pnl = new ControlPnl(analyzer);
    }

    @Test
    void testConstructor()
    {
        assertNotNull(pnl.getAnalyzer());
        assertNotNull(pnl.getSaveFileBtn());
        assertNotNull(pnl.getReRunBtn());
        assertNotNull(pnl.getQuitBtn());
        assertEquals("Save Tag List", pnl.getSaveFileBtn().getText());
        assertEquals("Re-run Program", pnl.getReRunBtn().getText());
        assertEquals("Quit", pnl.getQuitBtn().getText());
        int rows = ((GridLayout) pnl.getLayout()).getRows();
        int cols = ((GridLayout) pnl.getLayout()).getColumns();
        assertEquals(1, rows);
        assertEquals(3, cols);
    }
}