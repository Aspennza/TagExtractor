import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

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
