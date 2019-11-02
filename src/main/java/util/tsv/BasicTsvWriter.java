package main.java.util.tsv;

import com.univocity.parsers.tsv.TsvWriter;
import com.univocity.parsers.tsv.TsvWriterSettings;
import main.java.data.CrawlingConfig;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class BasicTsvWriter {
    private TsvWriterSettings tsvWriterSettings;
    private TsvWriter tsvWriter;
    private BufferedWriter bufferedWriter;
    private FileWriter fileWriter;

    public BasicTsvWriter() {
        try {
            tsvWriterSettings = new TsvWriterSettings();
            tsvWriterSettings.getFormat().setLineSeparator("\n");

            fileWriter = new FileWriter(CrawlingConfig.crawledTsvPath);
            bufferedWriter = new BufferedWriter(fileWriter);

            tsvWriter = new TsvWriter(bufferedWriter, tsvWriterSettings);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean writeTSV(List<Object[]> crawledList)
    {
        return true;
    }

}
