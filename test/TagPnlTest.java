import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.border.Border;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class TagPnlTest {

    TagPnl pnl;

    @BeforeEach
    void setUp() {
        pnl = new TagPnl();
    }

    @Test
    void testConstructor()
    {
        assertTrue(pnl.getLayout() instanceof BorderLayout);
        assertNotNull(pnl.getTagLbl());
        assertEquals("Tags and Frequencies:", pnl.getTagLbl().getText());
        assertNotNull(pnl.getTagTA());
        assertFalse(pnl.getTagTA().isEditable());
        assertNotNull(pnl.getScroller());
    }
}
