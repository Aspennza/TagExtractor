import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

//Functionally, FileCleaner will be the model, this file will be the control interacting with the view panels?
//i need a title panel
//i need a panel for displaying the name of the selected file
//i need a panel for displaying the tags and the frequencies
//i need control buttons for saving the file, replaying the program, and quitting
//finish programming
//do Junit
//do javadoc
//do UML
//Use the set as a filter for the map!! Don't store the words in the set

public class TagAnalyzer
{
    Set<String> keywords;
    Map<String, Integer> wordFreq;
    FileCleaner cleaner;

    public TagAnalyzer() {
        wordFreq = new TreeMap<>();
        cleaner = new FileCleaner();
        wordFreq = cleaner.removeStopWords();
    }

}
