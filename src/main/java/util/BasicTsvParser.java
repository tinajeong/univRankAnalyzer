package main.java.util;

import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BasicTsvParser {
    List<String[]> allRows;

    public BasicTsvParser() {
        allRows = new ArrayList<>();
    }

    public void readTSV() {
        TsvParserSettings settings = new TsvParserSettings();
        settings.getFormat().setLineSeparator("\n");

        // creates a TSV parser
        TsvParser parser = new TsvParser(settings);

        // parses all rows in one go.
        allRows = parser.parseAll(new File("output-onlinerandomtools.tsv"));
    }

    public void printTSV() {
        for(String[] arr1 : allRows)
            for(String arr2 : arr1){
                System.out.println(arr2);
            }
    }
}

