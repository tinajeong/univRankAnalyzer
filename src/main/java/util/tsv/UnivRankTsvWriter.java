package main.java.util.tsv;

import main.java.data.TSVConfig;
import main.java.data.UnivRankDTO;

import java.util.ArrayList;

public class UnivRankTsvWriter extends BasicTsvWriter {
    ArrayList<UnivRankDTO> crawledList;
    public UnivRankTsvWriter() {
        super();
        crawledList = new ArrayList<>();
    }

    @Override
    public void writeTSV() throws NullPointerException {
        if (crawledList != null) {
            //TODO writeHeaders의 파라미터 Collection으로 바꾸기
            tsvWriter.writeHeaders(TSVConfig.ColumnUnivRank, TSVConfig.ColumnUnivName, TSVConfig.ColumnUnivCountry);

            for (UnivRankDTO univRankDTO : crawledList) {
                tsvWriter.writeRow(univRankDTO.getRank(), univRankDTO.getUnivName(), univRankDTO.getCountry());
            }

            tsvWriter.close();
        }
    }

    public ArrayList<UnivRankDTO> getCrawledList() {
        return crawledList;
    }

    public void setCrawledList(ArrayList<UnivRankDTO> crawledList) {
        this.crawledList = crawledList;
    }
}
