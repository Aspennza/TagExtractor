import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import static java.nio.file.StandardOpenOption.*;

/**
 * Allows the creation of objects for saving the data in a Map<String, Integer> to a file
 * in CSV, JSON, or XML formats.
 * @author Zoe Aspenns aspennza@mail.uc.edu
 */
public class FileSaver
{
    //The number of files that have already been saved
    int filesSaved = 0;

    /**
     * This method accepts a map and a file format (CSV, JSON, or XML) and saves the data from the Map
     * to said file format.
     * @param map the Map to be saved to a file
     * @param fileFormat the int value (0, 1, or 2) representing CSV, JSON, or XML
     */
    public void saveFile(Map<String, Integer> map, int fileFormat)
    {
        Path fileLocation = nameFile(filesSaved);

        //This algorithm checks for exceptions before writing the file
        try
        {
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(fileLocation, CREATE, TRUNCATE_EXISTING, WRITE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            //This algorithm checks if the data should be saved as CSV, JSON, or XML
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

    /**
     * This method produces a unique name for the saved file based on how many files have already been saved.
     * @param filesSaved the number of files that have been saved.
     * @return a Path containing the location to save the file
     */
    public Path nameFile(int filesSaved)
    {
        File workingDirectory = new File(System.getProperty("user.dir"));
        return Paths.get(workingDirectory.getPath() + "\\src\\WordFrequencies" + (filesSaved + 1) + ".txt");
    }

    /**
     * A method for saving the Map data to CSV format.
     * @param map the Map containing the extracted tags
     * @return a Set<String> with all the content for a CSV file
     */
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

    /**
     * A method for saving the Map data to JSON format.
     * @param map the Map containing the extracted tags
     * @return a String with all the content for a JSON file
     */
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

    /**
     * A method for saving the Map data to XML format.
     * @param map the Map containing the extracted tags
     * @return a Set<String> with all the content for an XML file
     */
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
