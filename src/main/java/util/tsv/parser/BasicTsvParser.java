package main.java.util.tsv.parser;

import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;
import main.java.config.TSVConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BasicTsvParser {
    List<String[]> allRows;
    String TsvPath;

    public BasicTsvParser() {
        allRows = new ArrayList<>();
        TsvPath = null;
    }

    public BasicTsvParser(String tsvPath) {
        this();
        TsvPath = tsvPath;
    }

    public void setTsvPath(String tsvPath) {
        TsvPath = tsvPath;
    }

    public void readTSV() {
        TsvParserSettings settings = new TsvParserSettings();
        settings.getFormat().setLineSeparator("\n");

        TsvParser parser = new TsvParser(settings);
        if (TsvPath == null)
            allRows = parser.parseAll(new File(TSVConfig.getInstance().wordTsvPath));
        else
            allRows = parser.parseAll(new File(TsvPath));
    }

    public void printTSV() {
        System.out.println(this.TsvPath);
        System.out.println("======================");
        for (String[] strings : allRows) {
            for (String string : strings) {
                System.out.print(string + "\t");
            }
            System.out.println();
        }
    }
}