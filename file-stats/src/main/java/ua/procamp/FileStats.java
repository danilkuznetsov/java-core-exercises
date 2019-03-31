package ua.procamp;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

/**
 * {@link FileStats} provides an API that allow to get character statistic based on text file. All whitespace characters
 * are ignored.
 */
public class FileStats {

    private String fileContent;

    /**
     * Creates a new immutable {@link FileStats} objects using data from text file received as a parameter.
     *
     * @param fileName input text file name
     * @return new FileStats object created from text file
     */
    public static FileStats from(String fileName) {
        return new FileStats(fileName);
    }

    private FileStats(String fileName) {
        try (Stream<String> content = readLines(resolveFilePath(fileName))) {
            this.fileContent = content
                    .collect(joining(System.lineSeparator()))
                    // ignore all whitespaces
                    .replaceAll("\\s", "");
        }
    }

    private Stream<String> readLines(Path path) {
        try {
            return Files.lines(path);
        } catch (IOException e) {
            throw new FileStatsException("Cannot read file with name = " + path.toString());
        }
    }

    private Path resolveFilePath(String fileName) {
        try {
            URL file = resolveUrl(fileName);
            return Paths.get(file.toURI());
        } catch (URISyntaxException e) {
            throw new FileStatsException("Cannot resolve file with name = " + fileName);
        }
    }

    private URL resolveUrl(String fileName) {
        URL resource = getClass().getClassLoader().getResource(fileName);
        if (resource == null) {
            throw new FileStatsException("Cannot find file with name = " + fileName);
        }
        return resource;
    }

    /**
     * Returns a number of occurrences of the particular character.
     *
     * @param character a specific character
     * @return a number that shows how many times this character appeared in a text file
     */
    public int getCharCount(char character) {
        return (int) fileContent.chars().filter(i -> i == character).count();
    }

    /**
     * Returns a character that appeared most often in the text.
     *
     * @return the most frequently appeared character
     */
    public char getMostPopularCharacter() {
        Map<Character, Long> characters = fileContent.chars()
                .mapToObj(i -> (char) i)
                .collect(groupingBy(identity(), counting()));

        Map.Entry<Character, Long> popularCharacter = characters.entrySet()
                .stream()
                .max(comparing(Map.Entry::getValue))
                .orElseThrow(() -> new FileStatsException("File is empty"));

        return popularCharacter.getKey();
    }

    /**
     * Returns {@code true} if this character has appeared in the text, and {@code false} otherwise
     *
     * @param character a specific character to check
     * @return {@code true} if this character has appeared in the text, and {@code false} otherwise
     */
    public boolean containsCharacter(char character) {
        return fileContent.chars()
                .anyMatch(i -> i == character);
    }
}
