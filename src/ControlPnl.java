import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Allows the creation of pre-designed JPanel objects with JButton
 * controls for saving tags to a file, restarting the tag extractor,
 * and quitting the program.
 * @author Zoe Aspenns aspennza@mail.uc.edu
 */
public class ControlPnl extends JPanel
{
    //This JButton is used to save the filtered tags to a file
    JButton saveFileBtn;

    //This JButton is used to clear program state and restart the program
    JButton reRunBtn;

    //This JButton is used to quit the program
    JButton quitBtn;

    //This TagAnalyzer allows the ControlPnl to call methods from TagAnalyzer
    TagAnalyzer analyzer;

    //This constructor establishes the analyzer, gives the ControlPnl a layout, and establishes the functionality of each JButton
    public ControlPnl(TagAnalyzer analyzer) {
        this.analyzer = analyzer;
        setLayout(new GridLayout(1, 3));
        setBorder(new CompoundBorder(new EtchedBorder(), new EmptyBorder(10, 10, 10, 10)));

        saveFileBtn = new JButton("Save Tag List");
        reRunBtn = new JButton("Re-run Program");
        quitBtn = new JButton("Quit");

        add(saveFileBtn);

        saveFileBtn.addActionListener((ActionEvent ae) -> {
            analyzer.saveFile();
        });

        add(reRunBtn);

        reRunBtn.addActionListener((ActionEvent ae) -> {
            //This int tracks whether the user confirmed or denied they wanted to restart the program
            int selection = JOptionPane.showConfirmDialog(null, "Are you sure you want to reset the program?", "Reset", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            //This algorithm determines whether to reset the program based on the user's input
            if(selection == JOptionPane.YES_OPTION)
            {
                JOptionPane.showMessageDialog(null, "Resetting...");
                analyzer.resetProgram();
            } else {
                JOptionPane.showMessageDialog(null, "Your existing data will remain intact.");
            }
        });

        add(quitBtn);

        quitBtn.addActionListener((ActionEvent ae) -> {
            //This int tracks whether the user confirmed or denied they wanted to quit the program
            int selection = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit? You can press Re-run Program to reset the program.", "Quit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            //This algorithm determines whether to quit the program based on the user's input
            if(selection == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "Quitting the program...");
                System.exit(0);
            } else
            {
                JOptionPane.showMessageDialog(null, "The program will remain open.");
            }
        });
    }

    public JButton getSaveFileBtn() {
        return saveFileBtn;
    }

    public JButton getReRunBtn() {
        return reRunBtn;
    }

    public JButton getQuitBtn() {
        return quitBtn;
    }

    public TagAnalyzer getAnalyzer() {
        return analyzer;
    }
}
