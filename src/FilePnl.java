import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class FilePnl extends JPanel
{
    JLabel fileLbl;
    JTextField fileTF;

    public FilePnl()
    {
        setLayout(new GridLayout(1, 2));
        setBorder(new CompoundBorder(new EtchedBorder(), new EmptyBorder(10, 10, 10, 10)));

        fileLbl = new JLabel("Chosen File:");
        fileTF = new JTextField(30);

        add(fileLbl);
        add(fileTF);
    }

    public JLabel getFileLbl() {
        return fileLbl;
    }

    public JTextField getFileTF() {
        return fileTF;
    }
}
