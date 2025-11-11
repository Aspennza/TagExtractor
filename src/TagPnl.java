import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;

/**
 * Allows the creation of pre-formatted JPanel objects for displaying a list of filtered
 * tags and frequencies to a GUI.
 * @author Zoe Aspenns aspennza@mail.uc.edu
 */
public class TagPnl extends JPanel
{
    //This JLabel acts as a label for the tagTA
    JLabel tagLbl;

    //This JTextArea is used to display tags and frequencies
    JTextArea tagTA;

    //This JScrollPane allows tagTA to scroll
    JScrollPane scroller;

    //This constructor establishes the layout of the TagPnl and initializes the tagLbl, tagTA, and scroller
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

    public void setTagTA(JTextArea tagTA) {
        this.tagTA = tagTA;
    }
}
