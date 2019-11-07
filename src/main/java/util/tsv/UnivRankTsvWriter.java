package main.java.util.tsv;

import main.java.data.TSVConfig;
import main.java.data.UnivRankDTO;

import java.util.ArrayList;

public class UnivRankTsvWriter extends BasicTsvWriter {
    public UnivRankTsvWriter() {
        super();
    }

    @Override
    public void writeTSV(ArrayList<UnivRankDTO> crawledList) throws NullPointerException {
        if (crawledList != null) {
            //TODO writeHeaders의 파라미터 Collection으로 바꾸기
            tsvWriter.writeHeaders(TSVConfig.ColumnUnivRank, TSVConfig.ColumnUnivName, TSVConfig.ColumnUnivCountry);

            for (UnivRankDTO univRankDTO : crawledList) {
                tsvWriter.writeRow(univRankDTO.getRank(), univRankDTO.getUnivName(), univRankDTO.getCountry());
            }

            tsvWriter.close();
        }
    }
}
