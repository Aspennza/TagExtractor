import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import static java.nio.file.StandardOpenOption.CREATE;

public class FileCleaner
{
    JFileChooser chooser;
    File selectedFile;
    String rec;
    ArrayList<String> lines;
    String[] splitLines;
    Set<String> words;
    Set<String> stopWords;

    public FileCleaner()
    {
        chooser = new JFileChooser();
        rec = "";
        lines = new ArrayList<>();
        words = new TreeSet<>();
        stopWords = new TreeSet<>();
    }

    public Set<String> readFile(Path file)
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
                rec = reader.readLine();
                lines.add(rec);
            }
            reader.close();
            //call a method in the GUI to notify the user the file has been read

            for(String l : lines)
            {
                splitLines = l.split(" ");

                for(String word : splitLines)
                {
                    String cleanedWord = word.replaceAll("[^A-Za-z]", "").toLowerCase();
                    if(!cleanedWord.isEmpty()) {
                        tempSet.add(cleanedWord);
                    }
                }
            }
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

    public Set<String> chooseFile()
    {
        Set<String> tempSet = new TreeSet<>();

        //call a method in the TagAnalyzer to display a prompt via the GUI
        File workingDirectory = new File(System.getProperty("user.dir"));
        chooser.setCurrentDirectory(workingDirectory);

        if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            selectedFile = chooser.getSelectedFile();
            Path file = selectedFile.toPath();
            tempSet = readFile(file);
        }
        return tempSet;
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

    public Set<String> removeStopWords()
    {
        words = chooseFile();
        stopWords = chooseStopWords();

        words.removeAll(stopWords);

        return words;
    }
}
