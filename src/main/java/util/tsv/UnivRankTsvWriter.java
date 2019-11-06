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
    public void writeTSV(ArrayList<Object> crawledList) throws NullPointerException {
        List<UnivRankDTO> univRankDTOList = null;

        if (crawledList.get(0) instanceof UnivRankDTO)
            univRankDTOList = crawledList.stream().map(o -> (UnivRankDTO) o).collect(Collectors.toList());

        if (crawledList != null) {

            tsvWriter.writeHeaders(TSVConfig.ColumnUnivRank, TSVConfig.ColumnUnivName, TSVConfig.ColumnUnivCountry);

            for (UnivRankDTO univRankDTO : univRankDTOList) {
                tsvWriter.writeRow(univRankDTO.getRank(), univRankDTO.getUnivName(), univRankDTO.getCountry());
            }

            tsvWriter.close();
        }
    }
}
