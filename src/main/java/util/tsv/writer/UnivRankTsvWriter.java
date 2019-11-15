package main.java.util.tsv.writer;

import com.univocity.parsers.tsv.TsvWriter;
import main.java.config.TSVConfig;
import main.java.data.dto.UnivRankDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class UnivRankTsvWriter extends BasicTsvWriter {
    ArrayList<UnivRankDTO> crawledList;
    private final static Logger logger = LoggerFactory.getLogger(UnivRankTsvWriter.class);
    public UnivRankTsvWriter() {
        super();
        crawledList = new ArrayList<>();
    }

    @Override
    public void writeTSV() throws NullPointerException {
        if (crawledList != null) {
            tsvWriter.writeHeaders(TSVConfig.getInstance().getUnivColumns());
            for (UnivRankDTO univRankDTO : crawledList)
                tsvWriter.writeRow(univRankDTO.getRank(), univRankDTO.getUnivName(), univRankDTO.getCountry(),univRankDTO.getUnivInfoHref());
            tsvWriter.close();
        }
        else
            logger.error("crawledList is null");
    }

    @Override
    public void writeTSVWithTime() throws IOException {

        TsvPath = TSVConfig.getInstance().crawledRankTsvPath.replaceAll(".tsv", "") + super.getTsvPathWithTime() + ".tsv";
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
