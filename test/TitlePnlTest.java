import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class TitlePnlTest {

    TitlePnl pnl;

    @BeforeEach
    void setUp()
    {
        pnl = new TitlePnl();
    }

    @Test
    void testConstructor() {
        assertNotNull(pnl.getTitleLbl());
        assertEquals("Tag Extractor", pnl.getTitleLbl().getText());
        assertNotNull(pnl.getTitleFont());
        assertEquals("Serif", pnl.getTitleFont().getName());
        assertEquals(Font.BOLD, pnl.getTitleFont().getStyle());
        assertEquals(36, pnl.getTitleFont().getSize());
        assertEquals(pnl.getTitleFont(), pnl.getTitleLbl().getFont());
    }
}