import java.util.Set;
import java.util.TreeSet;

public class Main
{
    public static void main(String[] args)
    {
        FileCleaner cleaner = new FileCleaner();
        Set<String> exampleSet = new TreeSet<>();

        exampleSet = cleaner.chooseFile();
        System.out.println(exampleSet);
    }
}