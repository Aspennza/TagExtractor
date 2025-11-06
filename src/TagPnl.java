import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class TagPnl extends JPanel
{
    JLabel tagLbl;
    JTextArea tagTA;
    JScrollPane scroller;

    public TagPnl()
    {
        setBorder(new CompoundBorder(new EtchedBorder(), new EmptyBorder(10, 10, 10, 10)));
        setLayout(new BorderLayout());

        tagLbl = new JLabel("Tags and Frequencies:");
        tagTA = new JTextArea(10, 50);
        tagTA.setEditable(false);
        scroller = new JScrollPane(tagTA);

        add(tagLbl, BorderLayout.NORTH);
        add(scroller, BorderLayout.CENTER);
    }

    public JLabel getTagLbl() {
        return tagLbl;
    }

    public JTextArea getTagTA() {
        return tagTA;
    }

    public JScrollPane getScroller() {
        return scroller;
    }
}
