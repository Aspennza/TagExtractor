import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static java.nio.file.StandardOpenOption.CREATE;

/**
 * Allows the creation of objects for reading a file, normalizing the words in the file,
 * filtering out stop words, and counting each word by frequency.
 * @author Zoe Aspenns aspennza@mail.uc.edu
 */
public class FileCleaner
{
    /**
     * Accepts the Path to a file and a Set of stop words, reads the chosen file, splits it into words, normalizes it, and filters out the stop words.
     * @param file the user's chosen Path
     * @param stopWords a Set of words to remove from the final Map
     * @return a Map<String, Integer> containing all the non-stop-words in the file and their frequencies
     */
    public Map<String, Integer> readFile(Path file, Set<String> stopWords)
    {
        Map<String, Integer> tempMap = new TreeMap<>();
        String[] splitLines;

        //This algorithm checks for exceptions before reading the file
        try
        {
            InputStream in =
                    new BufferedInputStream(Files.newInputStream(file));
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(in));

            //This algorithm reads the file
            while(reader.ready())
            {
                String rec = reader.readLine();

                if (rec == null || rec.isBlank()) {
                    continue;
                }

                splitLines = rec.split("\\s+|--");

                    //This algorithm takes each word in the file, normalizes it, and excludes it from the final Map if it is a stop word
                    for(String word : splitLines)
                    {
                        String cleanedWord = word.replaceAll("[^A-Za-z]", "").toLowerCase();
                        if (!cleanedWord.isEmpty() && !stopWords.contains(cleanedWord)) {
                            tempMap.merge(cleanedWord, 1, Integer::sum);
                        }
                    }
            }
            reader.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("The file couldn't be found.");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            System.out.println("An exception occurred.");
            e.printStackTrace();
        }

        return tempMap;
    }

    /**
     * This method accepts a Path to a stop words file, reads it into a Set, and outputs that Set
     * @param file the user's chosen stop word file
     * @return a Set<String> of stop words
     */
    public Set<String> readStopWords(Path file)
    {
        Set<String> tempSet = new TreeSet<>();

        //This algorithm checks for exceptions while reading the file
        try
        {
            InputStream in =
                    new BufferedInputStream(Files.newInputStream(file));
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(in));

            //This algorithm reads the file
            while(reader.ready())
            {
                String line = reader.readLine();
                tempSet.add(line);
            }
            reader.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("The file couldn't be found.");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            System.out.println("An exception occurred.");
            e.printStackTrace();
        }

        return tempSet;
    }
}
