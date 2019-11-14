package main.java.util.tsv.writer;

import com.univocity.parsers.tsv.TsvWriter;
import main.java.config.TSVConfig;
import main.java.data.UnivInfoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class UnivInfoTsvWriter extends BasicTsvWriter {
    List<UnivInfoDTO> univInfoDTOList;
    private final static Logger logger = LoggerFactory.getLogger(UnivInfoTsvWriter.class);

    public UnivInfoTsvWriter() {
        super();
        this.TsvPath= TSVConfig.crawledInfoTsvPath;
    }

    public UnivInfoTsvWriter(List<UnivInfoDTO> univInfoDTOList) {
        this();
        this.univInfoDTOList = univInfoDTOList;
    }

    @Override
    public void writeTSV() {
        if (univInfoDTOList != null) {
            tsvWriter.writeHeaders(TSVConfig.getInstance().getUnivInfoColumns());
            for (UnivInfoDTO univInfoDTO : univInfoDTOList)
                tsvWriter.writeRow(univInfoDTO.getName(), univInfoDTO.getAddress(), univInfoDTO.getWebsite(),univInfoDTO.getSummary());
            tsvWriter.close();
        }
        else
            logger.error("univInfoDTOList is null");
    }

    @Override
    public void writeTSVWithTime() throws IOException {

        TsvPath = TSVConfig.getInstance().crawledInfoTsvPath.replaceAll(".tsv", "") + super.getTsvPathWithTime() + ".tsv";
        fileWriter = new FileWriter(TsvPath);
        bufferedWriter = new BufferedWriter(fileWriter);
        tsvWriter = new TsvWriter(bufferedWriter, tsvWriterSettings);

        writeTSV();
    }
    public List<UnivInfoDTO> getUnivInfoDTOList() {
        return univInfoDTOList;
    }

    public void setUnivInfoDTOList(List<UnivInfoDTO> univInfoDTOList) {
        this.univInfoDTOList = univInfoDTOList;
    }
}
