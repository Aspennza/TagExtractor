import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FileCleanerTest {

    FileCleaner cleaner;
    Path file1;
    Path file2;
    Path file3;
    Path file4;

    @BeforeEach
    void setUp()
    {
        cleaner = new FileCleaner();
        file1 = Paths.get( System.getProperty("user.dir"), "test", "A_very_short_file.txt");
        file2 = Paths.get(System.getProperty("user.dir"), "test", "stopwords.txt");
        file3 = Paths.get(System.getProperty("user.dir"), "test", "emptyfile.txt");
        file4 = Paths.get(System.getProperty("user.dir"), "test", "duplicate_words.txt");
    }

    @Test
    void readFile() {
        Set<String> stopWords = cleaner.readStopWords(file2);

        Map<String, Integer> fileWords = cleaner.readFile(file1, stopWords);

        assertEquals(3, fileWords.size());
        assertTrue(fileWords.containsKey("very"));
        assertTrue(fileWords.containsKey("short"));
        assertTrue(fileWords.containsKey("file"));
        assertFalse(fileWords.containsKey("A"));
        assertFalse(fileWords.containsKey("a"));
        assertEquals(1, fileWords.get("very"));
        assertEquals(1, fileWords.get("short"));
        assertEquals(1, fileWords.get("file"));

        fileWords = cleaner.readFile(file3, stopWords);
        assertTrue(fileWords.isEmpty());

        fileWords = cleaner.readFile(file4, stopWords);
        assertEquals(1, fileWords.size());
        assertTrue(fileWords.containsKey("word"));
        assertEquals(3, fileWords.get("word"));
    }

    @Test
    void readStopWords() {
        Set<String> stopWords = cleaner.readStopWords(file2);

        assertEquals(3, stopWords.size());
        assertTrue(stopWords.contains("a"));
        assertTrue(stopWords.contains("an"));
        assertTrue(stopWords.contains("the"));
    }
}