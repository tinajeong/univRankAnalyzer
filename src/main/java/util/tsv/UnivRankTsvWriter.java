package main.java.util.tsv;

import main.java.data.TSVConfig;
import main.java.data.UnivRankDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UnivRankTsvWriter extends BasicTsvWriter {
    public UnivRankTsvWriter() {
        super();
    }

    @Override
    public void writeTSV(ArrayList<UnivRankDTO> crawledList) throws NullPointerException {
        if (crawledList != null) {

            tsvWriter.writeHeaders(TSVConfig.ColumnUnivRank, TSVConfig.ColumnUnivName, TSVConfig.ColumnUnivCountry);

            for (UnivRankDTO univRankDTO : crawledList) {
                tsvWriter.writeRow(univRankDTO.getRank(), univRankDTO.getUnivName(), univRankDTO.getCountry());
            }

            tsvWriter.close();
        }
    }
}
