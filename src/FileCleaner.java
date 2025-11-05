import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import static java.nio.file.StandardOpenOption.CREATE;

//COME BACK TO WRITING FILECLEANER

public class FileCleaner
{
    JFileChooser chooser;
    File selectedFile;
    String rec;
    ArrayList<String> lines;
    String[] splitLines;
    Set<String> words;

    public FileCleaner()
    {
        chooser = new JFileChooser();
        rec = "";
        lines = new ArrayList<>();
        words = new TreeSet<>();
    }

    public TreeSet<String> readFile(Path file)
    {
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

                //Come back to writing here
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
    }

    public void chooseFile()
    {
        try
        {
            //call a method in the TagAnalyzer to display a prompt via the GUI
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                readFile(file);
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
    }
}
