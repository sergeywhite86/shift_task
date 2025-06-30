package org.sergey_white.util;


import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileUtils {

    private FileUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static void writeLinesToFile(List<String> lines, String path, boolean append) {
        Path filePath = Paths.get(path);
        try {
            Files.createDirectories(filePath.getParent());
            try (BufferedWriter writer = Files.newBufferedWriter(filePath,
                    StandardOpenOption.CREATE,
                    append ? StandardOpenOption.APPEND : StandardOpenOption.TRUNCATE_EXISTING)) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + filePath.toAbsolutePath());
        }
    }

    public static List<String> readAllLines(File file) {
        try {
            return Files.readAllLines(file.toPath());
        } catch (IOException e) {
            System.err.println("Error reading file: " + file.getAbsolutePath());
            return new ArrayList<>();
        }
    }
}
