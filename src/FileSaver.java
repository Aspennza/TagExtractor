import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class FileSaver
{
    public void saveFile(Map<String, Integer> map)
    {

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

    public Set<String> toJSON(Map<String, Integer> map)
    {
        Set<String> keys = map.keySet();
        Set<String> JSONValues = new TreeSet<>();
        String retString = "";
        char DQ = '\u0022';

        for(String key : keys)
        {
            retString = "{" + DQ + "Word" + DQ + ":" + DQ + key + DQ + ",";
            retString += " " + DQ + "Frequency" + DQ + ":" + map.get(key) + "}";
            JSONValues.add(retString);
        }

        return JSONValues;
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
