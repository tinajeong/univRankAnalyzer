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

    public void readTSV()
    {
        TsvParserSettings settings = new TsvParserSettings();
        settings.getFormat().setLineSeparator("\n");

        TsvParser parser = new TsvParser(settings);

        allRows = parser.parseAll(new File("output-onlinerandomtools.tsv"));
    }

    public void printTSV()
    {
        for(String[] strings: allRows)
        {
            for(String string: strings) {
                System.out.print(string+"\t");
            }
            System.out.println();
        }
    }
}

