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

/**
 * Allows the creation of JPanels with a JButton for choosing a file and
 * a JTextField for displaying its name.
 * @author Zoe Aspenns aspennza@mail.uc.edu
 */
public class FilePnl extends JPanel
{
    //A JLabel for the fileTF
    JLabel fileLbl;

    //A JTextField for displaying the name of the selected file
    JTextField fileTF;

    //A JButton for selecting a file
    JButton selectBtn;

    //A TagAnalyzer so that FilePnl can access the methods in TagAnalyzer
    TagAnalyzer analyzer;

    //This constructor initializes the analyzer, sets the layout of FilePnl, initializes its label, text field, and button, and creates an ActionListener for selectBtn
    public FilePnl(TagAnalyzer analyzer)
    {
        this.analyzer = analyzer;
        setLayout(new GridBagLayout());
        setBorder(new CompoundBorder(new EtchedBorder(), new EmptyBorder(10, 10, 10, 10)));

        //GridBagConstraints for the selectBtn
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.gridwidth = 1;
        gbc1.gridheight = 1;
        gbc1.fill = GridBagConstraints.BOTH;

        //GridBagConstraints for the fileLbl
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 1;
        gbc2.gridy = 0;
        gbc2.gridwidth = 1;
        gbc2.gridheight = 1;
        gbc2.fill = GridBagConstraints.NONE;
        gbc2.anchor = GridBagConstraints.EAST;
        gbc2.insets = new Insets(15, 15, 15, 15);

        //GridBagConstraints for the fileTF
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.gridx = 2;
        gbc3.gridy = 0;
        gbc3.gridwidth = 1;
        gbc3.gridheight = 1;
        gbc3.fill = GridBagConstraints.BOTH;

        fileLbl = new JLabel("Chosen File:");
        fileTF = new JTextField(30);
        fileTF.setEditable(false);
        selectBtn = new JButton("Choose a file");
        add(selectBtn, gbc1);

        selectBtn.addActionListener((ActionEvent ae) -> {
            analyzer.outputMap();
        });

        add(fileLbl, gbc2);
        add(fileTF, gbc3);
    }

    public JLabel getFileLbl() {
        return fileLbl;
    }

    public JTextField getFileTF() {
        return fileTF;
    }

    public JButton getSelectBtn() {
        return selectBtn;
    }

    public TagAnalyzer getAnalyzer() {
        return analyzer;
    }

    public void setFileTF(JTextField fileTF) {
        this.fileTF = fileTF;
    }

    public void setSelectBtn(JButton selectBtn) {
        this.selectBtn = selectBtn;
    }
}
