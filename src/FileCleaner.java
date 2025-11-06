import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static java.nio.file.StandardOpenOption.CREATE;

public class FileCleaner
{
    private JFileChooser chooser;
    private File selectedFile;
    private String rec;

    public FileCleaner()
    {
        chooser = new JFileChooser();
        rec = "";
    }

    public Map<String, Integer> readFile(Path file)
    {
        Map<String, Integer> tempMap = new TreeMap<>();
        String[] splitLines;

        try
        {
            InputStream in =
                    new BufferedInputStream(Files.newInputStream(file, CREATE));
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(in));

            while(reader.ready())
            {
                rec = reader.readLine();

                if (rec == null || rec.isBlank()) {
                    continue;
                }

                splitLines = rec.split("\\s+|--");

                    for(String word : splitLines)
                    {
                        String cleanedWord = word.replaceAll("[^A-Za-z]", "").toLowerCase();
                        if (!cleanedWord.isEmpty()) {
                            tempMap.merge(cleanedWord, 1, Integer::sum);
                        }
                    }
            }
            reader.close();
            //call a method in the GUI to notify the user the file has been read
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

    public Map<String, Integer> chooseFile()
    {
        Map<String, Integer> tempMap = new TreeMap<>();


        //call a method in the TagAnalyzer to display a prompt via the GUI
        File workingDirectory = new File(System.getProperty("user.dir"));
        chooser.setCurrentDirectory(workingDirectory);

        if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            selectedFile = chooser.getSelectedFile();
            Path file = selectedFile.toPath();
            tempMap = readFile(file);
        }
        return tempMap;
    }

    public Set<String> readStopWords()
    {
        //call a method in the TagAnalyzer to display a prompt via the GUI
        String filePath = "src/English_Stop_Words.txt";
        Set<String> tempSet = new TreeSet<>();

        try
        {
            InputStream in =
                    new BufferedInputStream(Files.newInputStream(Path.of(filePath), CREATE));
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(in));

            while(reader.ready())
            {
                tempSet.add(reader.readLine());
            }
            reader.close();
            //call a method in the GUI to notify the user the file has been read
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

    public Map<String, Integer> removeStopWords(Map<String, Integer> wordMap, Set<String> stopWordSet)
    {
        Map<String, Integer> result = new TreeMap<>(wordMap);

        for(String stopWord : stopWordSet) {
            result.remove(stopWord);
        }

        return result;
    }

    public void resetCleaner()
    {
        selectedFile = null;
        rec = "";
        chooser.setSelectedFile(null);
        chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
    }

    public JFileChooser getChooser() {
        return chooser;
    }

    public File getSelectedFile() {
        return selectedFile;
    }

    public String getRec() {
        return rec;
    }
}
