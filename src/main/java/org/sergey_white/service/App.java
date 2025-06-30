package org.sergey_white.service;

import java.util.ArrayList;
import java.util.List;

public class App {
    public void start(String[] args) {
        CommandLineArgs parsedArgs = parseCommandLine(args);

        if (parsedArgs.inputFiles().isEmpty()) {
            consoleLog("No input files provided.");
            return;
        }

        new FileFilter(
                parsedArgs.outputDir(),
                parsedArgs.prefix(),
                parsedArgs.appendMode(),
                parsedArgs.shortStat(),
                parsedArgs.fullStat()
        ).processFiles(parsedArgs.inputFiles().toArray(new String[0]));
    }

    private CommandLineArgs parseCommandLine(String[] args) {
        List<String> inputFiles = new ArrayList<>();
        String outputDir = ".";
        String prefix = "";
        boolean appendMode = false;
        boolean shortStat = false;
        boolean fullStat = false;

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];

            switch (arg) {
                case "-o" -> {
                    if (i + 1 < args.length) {
                        outputDir = args[++i];
                    } else {
                        consoleLog("Missing argument after -o");
                    }
                }
                case "-p" -> {
                    if (i + 1 < args.length) {
                        prefix = args[++i];
                    } else {
                        consoleLog("Missing argument after -p");
                    }
                }
                case "-a" -> appendMode = true;
                case "-s" -> shortStat = true;
                case "-f" -> fullStat = true;
                default -> inputFiles.add(arg);
            }
        }

        return new CommandLineArgs(outputDir, prefix, appendMode, shortStat, fullStat, inputFiles);
    }

    private static void consoleLog(String message) {
        System.err.println("Error: " + message);
    }
}