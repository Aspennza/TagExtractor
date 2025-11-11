import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FileSaverTest {

    FileSaver saver;

    @BeforeEach
    void setUp()
    {
        saver = new FileSaver();
    }

    @Test
    void nameFile() {
        Path testPath = saver.nameFile(1);
        Path expectedPath = Paths.get(System.getProperty("user.dir") + "\\src\\WordFrequencies" + 2 + ".txt");
        assertEquals(expectedPath, testPath);

        testPath = saver.nameFile(2);
        expectedPath = Paths.get(System.getProperty("user.dir") + "\\src\\WordFrequencies" + 3 + ".txt");
        assertEquals(expectedPath, testPath);
    }

    @Test
    void toCSV() {
        Map<String, Integer> testMap = Map.of("cat", 2, "dog", 3);
        Set<String> output = saver.toCSV(testMap);

        assertTrue(output.contains("cat, 2".trim()));
        assertTrue(output.contains("dog, 3".trim()));

        assertTrue(saver.toCSV(Collections.emptyMap()).isEmpty());
    }

    @Test
    void toJSON() {
        Map<String, Integer> testMap = Map.of("cat", 2, "dog", 3);
        String output = saver.toJSON(testMap);
        assertNotNull(output);
        assertTrue(output.contains("{\"Word\":\"cat\", \"Frequency\":2}"));
        assertTrue(output.contains("{\"Word\":\"dog\", \"Frequency\":3}"));
        assertEquals("[]", saver.toJSON(Collections.emptyMap()));
    }

    @Test
    void toXML() {
        Map<String, Integer> testMap = Map.of("cat", 2, "dog", 3);
        Set<String> output = saver.toXML(testMap);

        assertTrue(output.contains("<WordFrequency><Word>cat</Word><Frequency>2</Frequency></WordFrequency>"));
        assertTrue(output.contains("<WordFrequency><Word>dog</Word><Frequency>3</Frequency></WordFrequency>"));
        assertTrue(saver.toXML(Collections.emptyMap()).isEmpty());
    }
}