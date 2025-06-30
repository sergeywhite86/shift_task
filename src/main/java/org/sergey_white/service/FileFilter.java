package org.sergey_white.service;

import org.sergey_white.enums.FileType;
import org.sergey_white.util.FileUtils;

import java.io.*;
import java.util.*;

public class FileFilter {
    private final Map<FileType, List<String>> results = new HashMap<>();
    private final Statistics stats = new Statistics();

    private final String outputDir;
    private final String prefix;
    private final boolean appendMode;
    private final boolean fullStat;
    private final boolean shortStat;

    public FileFilter(String outputDir, String prefix, boolean appendMode, boolean shortStat, boolean fullStat) {
        this.outputDir = outputDir;
        this.prefix = prefix;
        this.appendMode = appendMode;
        this.shortStat = shortStat;
        this.fullStat = fullStat;

        results.put(FileType.INTEGER, new ArrayList<>());
        results.put(FileType.FLOAT, new ArrayList<>());
        results.put(FileType.STRING, new ArrayList<>());
    }

    public void processFiles(String... files) {
        for (String fileName : files) {
            File file = new File(fileName);
            if (!file.exists()) {
                System.err.println("File not found: " + fileName);
                continue;
            }
            List<String> lines = FileUtils.readAllLines(file);
            for (String line : lines) {
                classifyLine(line.trim());
            }
        }

        writeResults();
        printStatistics();
    }

    private void classifyLine(String line) {
        if (line.isEmpty()) return;

        try {
            long l = Long.parseLong(line);
            results.get(FileType.INTEGER).add(line);
            stats.addInteger(l);
            return;
        } catch (NumberFormatException ignored) {}

        try {
            double d = Double.parseDouble(line);
            results.get(FileType.FLOAT).add(line);
            stats.addFloat(d);
            return;
        } catch (NumberFormatException ignored) {}

        results.get(FileType.STRING).add(line);
        stats.addString(line);
    }

    private void writeResults() {
        for (Map.Entry<FileType, List<String>> entry : results.entrySet()) {
            List<String> lines = entry.getValue();
            if (lines.isEmpty()) continue;

            String suffix;
            switch (entry.getKey()) {
                case INTEGER -> suffix = "integers.txt";
                case FLOAT -> suffix = "floats.txt";
                default -> suffix = "strings.txt";
            }

            String filename = outputDir + File.separator + prefix + suffix;
                FileUtils.writeLinesToFile(lines, filename, appendMode);
        }
    }

    private void printStatistics() {
        if (shortStat) stats.printShort();
        if (fullStat) stats.printFull();
    }
}
