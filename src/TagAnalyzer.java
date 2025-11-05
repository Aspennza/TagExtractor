import java.util.Set;
import java.util.TreeSet;

//COME BACK TO WRITING FILECLEANER

public class TagAnalyzer
{
    Set<String> keywords;
    Set<String> stopWords;

    public TagAnalyzer() {
        keywords = new TreeSet<>();
        stopWords = new TreeSet<>();
    }

}
