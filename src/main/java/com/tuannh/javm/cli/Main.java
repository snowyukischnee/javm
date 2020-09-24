package com.tuannh.javm.cli;

import org.apache.commons.cli.*;

public class Main {
    private static Options options;

    static {
        options = new Options();
        options.addOption("cp", "classpath", true, "class path");
    }

    // parse args
    private static CommandLine argParse(String[] args) throws ParseException {
        CommandLineParser parser = new DefaultParser();
        return parser.parse(options, args);
    }

    public static void main(String[] args) throws ParseException {
        CommandLine cmd = argParse(args);

    }
}
