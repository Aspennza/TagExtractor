import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class FilePnl extends JPanel
{
    JLabel fileLbl;
    JTextField fileTF;
    JButton selectBtn;
    TagAnalyzer analyzer;

    public FilePnl(TagAnalyzer analyzer)
    {
        this.analyzer = analyzer;
        setLayout(new GridLayout(1, 3));
        setBorder(new CompoundBorder(new EtchedBorder(), new EmptyBorder(10, 10, 10, 10)));

        fileLbl = new JLabel("Chosen File:");
        fileTF = new JTextField(30);
        fileTF.setEditable(false);
        selectBtn = new JButton("Choose a file");
        add(selectBtn);

        selectBtn.addActionListener((ActionEvent ae) -> {
            analyzer.outputMap();
        });

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
