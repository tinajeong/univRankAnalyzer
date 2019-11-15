package main.java.util.tsv.parser;

import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;
import main.java.config.TSVConfig;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class WordTsvParser extends BasicTsvParser {
    private ArrayList<String> wordList;
    private BufferedReader bufferedReader;
    private FileReader fileReader;
    private TsvParser tsvParser;

    public WordTsvParser() {
        super();
        wordList = new ArrayList<String>();

        TsvParserSettings settings = new TsvParserSettings();
        settings.getFormat().setLineSeparator("\n");

        tsvParser = new TsvParser(settings);
    }

    @Override
    public void readTSV() {
        try {
            fileReader = new FileReader(TSVConfig.getInstance().wordTsvPath);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        bufferedReader = new BufferedReader(fileReader);

        allRows = tsvParser.parseAll(bufferedReader);

        for(String[] strings:allRows) {
            for (String string : strings)
            {
                if(string.length()< 5)
                {
                    wordList.add(string);
                }
            }
        }
    }

    @Override
    public void printTSV() {
        System.out.println("wordList");
        System.out.println("======================");

//        for(String string: wordList)
//        {
//            System.out.println(string);
//        }

        wordList.forEach(s->{
            System.out.println(s);
        });
    }
}
