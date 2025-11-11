import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

//do javadoc
//do UML

/**
 * Allows the creation of objects for extracting useful tags from a file, displaying them to a GUI,
 * and offering the option to save them to a file.
 * @author Zoe Aspenns aspennza@mail.uc.edu
 */
public class TagAnalyzer
{
    //A JFrame for displaying the GUI components
    private JFrame frame;

    //A TitlePnl object containing the logic for formatting the titlePnl
    private TitlePnl titlePnl;

    //A FilePnl object containing the logic for formatting the filePnl
    private FilePnl filePnl;

    //A TagPnl object containing the logic for formatting the tagPnl
    private TagPnl tagPnl;

    //A ControlPnl object containing the logic for formatting the controlPnl
    private ControlPnl controlPnl;

    //A Set<String> for storing the list of stop words
    private Set<String> stopWords;

    //A Map<String, Integer> for storing words and their frequencies from a file
    private Map<String, Integer> wordFreq;

    //A FileCleaner for reading a file, normalizing it, and filtering stop words
    private FileCleaner cleaner;

    //A FileSaver for saving the list of tags and frequencies to a file
    private FileSaver saver;

    //A FileChooserLauncher for prompting JFileChooser input
    private FileChooserLauncher chooser1;

    //A FileChooserLauncher for prompting JFileChooser input
    private FileChooserLauncher chooser2;

    /**
     * This method initializes wordFreq, the cleaner, the saver, and the two FileChooserLaunchers, as well as establishing the JFrame
     */
    public void start()
    {
        wordFreq = new TreeMap<>();
        cleaner = new FileCleaner();
        saver = new FileSaver();
        chooser1 = new FileChooserLauncher();
        chooser2 = new FileChooserLauncher();
        generateFrame();
    }

    /**
     * This method prompts the user to choose a file to search and a stop word file for filtering, filters the file, and outputs the result to the tagTA
     * @return a Map<String, Integer> containing the list of words and frequencies
     */
    public Map<String, Integer> outputMap()
    {
        JOptionPane.showMessageDialog(null, "First, choose the file you want to extract tags from. Then, select the file with the words you want to remove.");
        Path selectedFile = chooser1.chooseFile();

        //This algorithm checks that the user chose a file before acting on it
        if(selectedFile != null)
        {
            Path stopWordFile = chooser2.chooseFile();

            //This algorithm checks that the user chose a stop words file before acting on it
            if(stopWordFile != null)
            {
                stopWords = cleaner.readStopWords(stopWordFile);
                wordFreq = cleaner.readFile(selectedFile, stopWords);
                filePnl.getFileTF().setText(selectedFile.getFileName().toString());
                filePnl.getSelectBtn().setEnabled(false);

                Set<String> keySet = wordFreq.keySet();

                tagPnl.getTagTA().setText("");

                //This algorithm appends the words and frequencies to the text area
                for(String key : keySet)
                {
                    tagPnl.getTagTA().append("Word: " + key + "; Frequency: " + wordFreq.get(key) + "\n");
                }
            }
        }
        return wordFreq;
    }

    /**
     * This method establishes the JFrame, its layout, the panels, and other technicalities, like the size or visibility of the frame.
     */
    public void generateFrame() {
        frame = new JFrame();

        //GridBagConstraints for the titlePnl
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.gridwidth = 1;
        gbc1.gridheight = 1;
        gbc1.weightx = 1;
        gbc1.fill = GridBagConstraints.BOTH;

        //GridBagConstraints for the filePnl
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 0;
        gbc2.gridy = 1;
        gbc2.gridwidth = 1;
        gbc2.gridheight = 1;
        gbc2.weightx = 1;
        gbc2.fill = GridBagConstraints.BOTH;

        //GridBagConstraints for the tagPnl
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.gridx = 0;
        gbc3.gridy = 2;
        gbc3.gridwidth = 1;
        gbc3.gridheight = 3;
        gbc3.weightx = 1;
        gbc3.weighty = 1;
        gbc3.fill = GridBagConstraints.BOTH;

        //GridBagConstraints for the controlPnl
        GridBagConstraints gbc4 = new GridBagConstraints();
        gbc4.gridx = 0;
        gbc4.gridy = 5;
        gbc4.gridwidth = 1;
        gbc4.gridheight = 1;
        gbc4.weightx = 1;
        gbc4.fill = GridBagConstraints.BOTH;

        //A panel for containing all other GUI elements
        JPanel mainPnl = new JPanel();

        //This Toolkit is used to find the screen size of the computer running the GUI
        Toolkit kit = Toolkit.getDefaultToolkit();

        //This Dimension stores the screen size
        Dimension screenSize = kit.getScreenSize();

        //This int stores the height of the screen
        int screenHeight = screenSize.height;

        //This int stores the width of the screen
        int screenWidth = screenSize.width;

        mainPnl.setLayout(new GridBagLayout());
        frame.add(mainPnl);

        titlePnl = new TitlePnl();
        mainPnl.add(titlePnl, gbc1);

        filePnl = new FilePnl(this);
        mainPnl.add(filePnl, gbc2);

        tagPnl = new TagPnl();
        mainPnl.add(tagPnl, gbc3);

        controlPnl = new ControlPnl(this);
        mainPnl.add(controlPnl, gbc4);

        frame.setSize(screenWidth * 3 / 4, screenHeight * 3 / 4);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Tag Extractor");
        frame.setVisible(true);
    }

    /**
     * This method checks that a file has been effectively filtered, then allows the user to pick a file format to save the final tags and frequencies to.
     */
    public void saveFile()
    {
        Object[] formatOptions = {"CSV", "JSON", "XML"};

        //This algorithm checks that the user has previously called the file filtering methods before saving
        if(wordFreq == null || wordFreq.isEmpty() || stopWords == null || stopWords.isEmpty()) {
            JOptionPane.showMessageDialog(null, "You must select both a text file and a stop words file before saving.");
        }
        else {
            //This int tracks what format the user wants to save their file to
            int selection = JOptionPane.showOptionDialog(null, "What format would you like to save your file to?", "Save File", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, formatOptions, formatOptions[0]);

            //This algorithm determines how to save the file based on the user's input
            if (selection == 0) {
                saver.saveFile(wordFreq, 0);
                JOptionPane.showMessageDialog(null, "Saving file...");
            } else if (selection == 1) {
                saver.saveFile(wordFreq, 1);
                JOptionPane.showMessageDialog(null, "Saving file...");
            } else if (selection == 2) {
                saver.saveFile(wordFreq, 2);
                JOptionPane.showMessageDialog(null, "Saving file...");
            }
            else {
                JOptionPane.showMessageDialog(null, "The dialog was closed without a selection. Save canceled.");
            }
        }
    }

    /**
     * This method clears all relevant text areas, Maps, file choosers, and settings between two program runs
     */
    public void resetProgram()
    {
        filePnl.getFileTF().setText("");
        filePnl.getSelectBtn().setEnabled(true);
        tagPnl.getTagTA().setText("");
        wordFreq = new TreeMap<>();
        chooser1.resetChooser();
        chooser2.resetChooser();
    }

    public JFrame getFrame() {
        return frame;
    }

    public TitlePnl getTitlePnl() {
        return titlePnl;
    }

    public FilePnl getFilePnl() {
        return filePnl;
    }

    public TagPnl getTagPnl() {
        return tagPnl;
    }

    public ControlPnl getControlPnl() {
        return controlPnl;
    }

    public Set<String> getStopWords() {
        return stopWords;
    }

    public Map<String, Integer> getWordFreq() {
        return wordFreq;
    }

    public FileCleaner getCleaner() {
        return cleaner;
    }

    public FileSaver getSaver() {
        return saver;
    }

    public FileChooserLauncher getChooser1() {
        return chooser1;
    }

    public FileChooserLauncher getChooser2() {
        return chooser2;
    }

    public void setWordFreq(Map<String, Integer> wordFreq) {
        this.wordFreq = wordFreq;
    }
}
