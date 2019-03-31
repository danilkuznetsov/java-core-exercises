package ua.procamp;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * {@link FileReaders} privides an API that allow to read whole file into a {@link String} by file name.
 */
public class FileReaders {

    /**
     * Returns a {@link String} that contains whole text from the file specified by name.
     *
     * @param fileName a name of a text file
     * @return string that holds whole file content
     */

    public static String readWholeFile(String fileName) {
        try (Stream<String> content = readLines(resolveFilePath(fileName))) {
            return content.collect(Collectors.joining(System.lineSeparator()));
        }
    }

    private static Stream<String> readLines(Path filePath) {
        try {
            return Files.lines(filePath);
        } catch (IOException e) {
            throw new FileReadersException("Cannot read file with name = " + filePath.toString());
        }
    }

    private static Path resolveFilePath(String fileName) {
        try {
            URL file = resolveUrl(fileName);
            return Paths.get(file.toURI());
        } catch (URISyntaxException e) {
            throw new FileReadersException("Cannot resolve file with name = " + fileName);
        }
    }

    private static URL resolveUrl(String fileName) {
        URL resource = FileReaders.class.getClassLoader().getResource(fileName);
        if (resource == null) {
            throw new FileReadersException("Cannot find file with name = " + fileName);
        }
        return resource;
    }
}
