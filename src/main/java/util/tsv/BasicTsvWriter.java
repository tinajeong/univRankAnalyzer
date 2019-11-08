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
    public String TsvPath;

    public BasicTsvWriter() {
        try {
            tsvWriterSettings = new TsvWriterSettings();
            tsvWriterSettings.getFormat().setLineSeparator("\n");
            TsvPath = TSVConfig.crawledTsvPath;
            fileWriter = new FileWriter(TsvPath);
            bufferedWriter = new BufferedWriter(fileWriter);

            tsvWriter = new TsvWriter(bufferedWriter, tsvWriterSettings);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void writeTSV() {
    }

    public BufferedWriter getBufferedWriter() {
        return bufferedWriter;
    }

    public void setBufferedWriter(BufferedWriter bufferedWriter) {
        this.bufferedWriter = bufferedWriter;
    }

    public FileWriter getFileWriter() {
        return fileWriter;
    }

    public void setFileWriter(FileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

    public String getTsvPath() {
        return TsvPath;
    }

    public void setTsvPath(String tsvPath) {
        TsvPath = tsvPath;
    }
}
