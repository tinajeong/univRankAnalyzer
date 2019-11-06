package main.java.util.tsv;

import com.univocity.parsers.tsv.TsvWriter;
import com.univocity.parsers.tsv.TsvWriterSettings;
import main.java.data.TSVConfig;
import main.java.data.UnivRankDTO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class BasicTsvWriter {
    public TsvWriterSettings tsvWriterSettings;
    public TsvWriter tsvWriter;
    public BufferedWriter bufferedWriter;
    public FileWriter fileWriter;

    public BasicTsvWriter() {
        try {
            tsvWriterSettings = new TsvWriterSettings();
            tsvWriterSettings.getFormat().setLineSeparator("\n");

            fileWriter = new FileWriter(TSVConfig.crawledTsvPath);
            bufferedWriter = new BufferedWriter(fileWriter);

            tsvWriter = new TsvWriter(bufferedWriter, tsvWriterSettings);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void writeTSV(ArrayList<Object> crawledList) {
    }
}
