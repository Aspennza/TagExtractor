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
    private ArrayList<String> lines;
    private String[] splitLines;
    private Map<String, Integer> words;
    private Set<String> stopWords;

    public FileCleaner()
    {
        chooser = new JFileChooser();
        rec = "";
        lines = new ArrayList<>();
        words = new TreeMap<>();
        stopWords = new TreeSet<>();
    }

    public Map<String, Integer> readFile(Path file)
    {
        Map<String, Integer> tempMap = new TreeMap<>();

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

                splitLines = rec.split(" ");

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

    public Set<String> chooseStopWords()
    {
        Set<String> tempSet = new TreeSet<>();

        //call a method in the TagAnalyzer to display a prompt via the GUI
        File workingDirectory = new File(System.getProperty("user.dir"));
        chooser.setCurrentDirectory(workingDirectory);

        if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            selectedFile = chooser.getSelectedFile();
            Path file = selectedFile.toPath();
            tempSet = readStopWords(file);
        }
        return tempSet;
    }

    public Set<String> readStopWords(Path file)
    {
        Set<String> tempSet = new TreeSet<>();

        try
        {
            InputStream in =
                    new BufferedInputStream(Files.newInputStream(file, CREATE));
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
        for(String stopWord : stopWords) {
            words.remove(stopWord);
        }

        return words;
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

    public ArrayList<String> getLines() {
        return lines;
    }

    public String[] getSplitLines() {
        return splitLines;
    }

    public Map<String, Integer> getWords() {
        return words;
    }

    public Set<String> getStopWords() {
        return stopWords;
    }
}
