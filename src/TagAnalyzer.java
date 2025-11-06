import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

//Functionally, FileCleaner will be the model, this file will be the control interacting with the view panels?
//finish programming
//do Junit
//do javadoc
//do UML
//Use the set as a filter for the map!! Don't store the words in the set

public class TagAnalyzer
{
    private JFrame frame;
    private TitlePnl titlePnl;
    private FilePnl filePnl;
    private TagPnl tagPnl;
    private ControlPnl controlPnl;
    private Set<String> stopWords;
    private Map<String, Integer> wordFreq;
    private FileCleaner cleaner;

    public TagAnalyzer() {
        wordFreq = new TreeMap<>();
        cleaner = new FileCleaner();
    }

    public Map<String, Integer> outputMap()
    {
        Map<String, Integer> tempMap = new TreeMap<>();
        tempMap = cleaner.chooseFile();
        stopWords = cleaner.chooseStopWords();
        wordFreq = cleaner.removeStopWords(wordFreq, stopWords);

        filePnl.getFileTF().setText(cleaner.getSelectedFile().getName());

        Set<String> keySet = wordFreq.keySet();

        for(String key : keySet)
        {
            tagPnl.getTagTA().append("Word: " + key + "; Frequency: " + wordFreq.get(key) + "\n");
        }

        return wordFreq;
    }

    public void generateFrame() {
        frame = new JFrame();
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.gridwidth = 1;
        gbc1.gridheight = 1;
        gbc1.fill = GridBagConstraints.BOTH;
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 0;
        gbc2.gridy = 1;
        gbc2.gridwidth = 1;
        gbc2.gridheight = 1;
        gbc2.fill = GridBagConstraints.BOTH;
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.gridx = 0;
        gbc3.gridy = 2;
        gbc3.gridwidth = 1;
        gbc3.gridheight = 3;
        gbc3.fill = GridBagConstraints.BOTH;
        GridBagConstraints gbc4 = new GridBagConstraints();
        gbc4.gridx = 0;
        gbc4.gridy = 3;
        gbc4.gridwidth = 1;
        gbc4.gridheight = 1;
        gbc4.fill = GridBagConstraints.BOTH;

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

        frame.setSize(screenWidth, screenHeight);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Tag Extractor");
        frame.setVisible(true);
    }

}
