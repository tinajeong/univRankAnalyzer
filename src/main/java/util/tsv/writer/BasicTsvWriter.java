package main.java.util.tsv.writer;

import com.univocity.parsers.tsv.TsvWriter;
import com.univocity.parsers.tsv.TsvWriterSettings;
import main.java.config.TSVConfig;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
            TsvPath = TSVConfig.getInstance().crawledRankTsvPath;
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
    public void writeTSVWithTime() throws IOException{}

    public String getTsvPathWithTime() throws IOException {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("hh_dd_MM_yyyy");
        String formattedDate =formatter.format(date);

        return formattedDate;
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
