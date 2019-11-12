package main.java.util.tsv;

import main.java.data.UnivRankDTO;
import main.java.util.analyzer.dao.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class UnivRankTsvParser extends BasicTsvParser {
    ArrayList<UnivRankDTO> univRankList;
    private final static Logger logger = LoggerFactory.getLogger(UnivRankTsvParser.class);

    public UnivRankTsvParser() {
        super();
        univRankList = new ArrayList<>();
    }

    public UnivRankTsvParser(ArrayList<UnivRankDTO> univRankList) {
        this.univRankList = univRankList;
    }

    @Override
    public void readTSV() {
        super.readTSV();
        for (String[] strArr : allRows) {
            if (allRows.indexOf(strArr) == 0)
                continue;
            UnivRankDTO univRankDTO = new UnivRankDTO(strArr[1], strArr[2], Long.valueOf(strArr[0]));
            univRankList.add(univRankDTO);
        }
    }


    @Override
    public void printTSV() {
        super.printTSV();
    }


    public ArrayList<UnivRankDTO> getUnivRankList() {
        return univRankList;
    }

    public void setUnivRankList(ArrayList<UnivRankDTO> univRankList) {
        this.univRankList = univRankList;
    }
}
