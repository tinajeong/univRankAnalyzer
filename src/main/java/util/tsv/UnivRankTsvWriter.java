package main.java.util.tsv;

import com.univocity.parsers.tsv.TsvWriter;
import main.java.data.TSVConfig;
import main.java.data.UnivRankDTO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UnivRankTsvWriter extends BasicTsvWriter {
    ArrayList<UnivRankDTO> crawledList;

    public UnivRankTsvWriter() {
        super();
        crawledList = new ArrayList<>();
    }

    @Override
    public void writeTSV() throws NullPointerException {
        if (crawledList != null) {
            tsvWriter.writeHeaders(TSVConfig.getInstance().getUnivColumns());
            for (UnivRankDTO univRankDTO : crawledList)
                tsvWriter.writeRow(univRankDTO.getRank(), univRankDTO.getUnivName(), univRankDTO.getCountry());
            tsvWriter.close();
        }
    }

    public void writeTSVWithTime() throws IOException {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy");
        System.out.println(formatter.format(date));
        TsvPath = TSVConfig.crawledTsvPath.replaceAll(".tsv", "") + formatter.format(date) + ".tsv";
        fileWriter = new FileWriter(TsvPath);
        bufferedWriter = new BufferedWriter(fileWriter);
        tsvWriter = new TsvWriter(bufferedWriter, tsvWriterSettings);

        writeTSV();
    }

    public ArrayList<UnivRankDTO> getCrawledList() {
        return crawledList;
    }

    public void setCrawledList(ArrayList<UnivRankDTO> crawledList) {
        this.crawledList = crawledList;
    }
}
