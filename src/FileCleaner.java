import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static java.nio.file.StandardOpenOption.CREATE;

public class FileCleaner
{

    public Map<String, Integer> readFile(Path file, Set<String> stopWords)
    {
        Map<String, Integer> tempMap = new TreeMap<>();
        String[] splitLines;

        try
        {
            InputStream in =
                    new BufferedInputStream(Files.newInputStream(file));
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(in));

            while(reader.ready())
            {
                String rec = reader.readLine();

                if (rec == null || rec.isBlank()) {
                    continue;
                }

                splitLines = rec.split("\\s+|--");

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

    public Set<String> readStopWords(Path file)
    {
        Set<String> tempSet = new TreeSet<>();

        try
        {
            InputStream in =
                    new BufferedInputStream(Files.newInputStream(file));
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(in));

            while(reader.ready())
            {
                String line = reader.readLine();

                String cleanedLine = line.replaceAll("[^A-Za-z]", "").toLowerCase();

                if(!cleanedLine.isEmpty()) {
                    tempSet.add(cleanedLine);
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

        return tempSet;
    }
}
