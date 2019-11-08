package main.java.util.tsv;

import com.univocity.parsers.tsv.TsvWriter;
import com.univocity.parsers.tsv.TsvWriterSettings;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BasicTsvWriter {
    private BufferedWriter bufferedWriter;
    private FileWriter fileWriter;
    private TsvWriter tsvWriter;

    public BasicTsvWriter() throws IOException {
        TsvWriterSettings settings = new TsvWriterSettings();
        settings.getFormat().setLineSeparator("\n");

        fileWriter = new FileWriter("");
        bufferedWriter = new BufferedWriter(fileWriter);
        tsvWriter = new TsvWriter(bufferedWriter, settings);
    }

    public boolean writeTSV(List<Object[]> crawledList)
    {
        return true;
    }
}
