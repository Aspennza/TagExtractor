/**
 * Creates a TagAnalyzer object so that the TagAnalyzer.java program can be tested and run.
 * @author Zoe Aspenns aspennza@mail.uc.edu
 */
public class TagAnalyzerRunner
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        //This TagAnalyzer creates an instance of the TagAnalyzer.java class
        TagAnalyzer analyzer = new TagAnalyzer();
        analyzer.start();
    }
}