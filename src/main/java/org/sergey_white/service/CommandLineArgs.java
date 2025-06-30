package org.sergey_white.service;

import java.util.List;

public record CommandLineArgs(
        String outputDir,
        String prefix,
        boolean appendMode,
        boolean shortStat,
        boolean fullStat,
        List<String> inputFiles
) {}