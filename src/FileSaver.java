import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static java.nio.file.StandardOpenOption.*;

public class FileSaver
{
    int filesSaved = 0;

    public void saveFile(Map<String, Integer> map, int fileFormat)
    {
        Path fileLocation = nameFile(filesSaved);

        try
        {
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(fileLocation, CREATE, TRUNCATE_EXISTING, WRITE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            if(fileFormat == 0) //CSV
            {
                Set<String> CSVSet = toCSV(map);

                for(String csv : CSVSet)
                {
                    writer.write(csv, 0, csv.length());
                    writer.newLine();
                }
                writer.close();

            } else if (fileFormat == 1) //JSON
            {
                String json = toJSON(map);
                writer.write(json, 0, json.length());
                writer.newLine();
                writer.close();
            } else //XML
            {
                Set<String> XMLSet = toXML(map);

                for(String xml : XMLSet) {
                    writer.write(xml, 0, xml.length());
                    writer.newLine();
                }
                writer.close();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        filesSaved++;
    }


    public Path nameFile(int filesSaved)
    {
        File workingDirectory = new File(System.getProperty("user.dir"));
        return Paths.get(workingDirectory.getPath() + "\\src\\WordFrequencies" + (filesSaved + 1) + ".txt");
    }

    public Set<String> toCSV(Map<String, Integer> map)
    {
        Set<String> keys = map.keySet();
        Set<String> CSVValues = new TreeSet<>();

        for(String key : keys)
        {
            CSVValues.add(key + ", " + map.get(key));
        }
        return CSVValues;
    }

    public String toJSON(Map<String, Integer> map)
    {
        Set<String> keys = map.keySet();
        String retString = "[";
        char DQ = '\u0022';
        boolean first = true;

        for(String key : keys)
        {
            if(!first) {
                retString += ", ";
            }
            retString += "{" + DQ + "Word" + DQ + ":" + DQ + key + DQ + ",";
            retString += " " + DQ + "Frequency" + DQ + ":" + map.get(key) + "}";
            first = false;
        }

        retString += "]";

        return retString;
    }

    public Set<String> toXML(Map<String, Integer> map)
    {
        Set<String> keys = map.keySet();
        Set<String> XMLValues = new TreeSet<>();
        String retString = "";

        for(String key : keys)
        {
            retString = "<WordFrequency>";
            retString += "<Word>" + key + "</Word>";
            retString += "<Frequency>" + map.get(key) + "</Frequency></WordFrequency>";
            XMLValues.add(retString);
        }

        return XMLValues;
    }
}
