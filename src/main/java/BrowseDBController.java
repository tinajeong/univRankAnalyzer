package main.java;

import main.java.util.analyzer.dao.HibernateUtil;
import main.java.util.tsv.UnivRankTsvParser;

public class BrowseDBController {
    public static void main(String[] args)
    {
        UnivRankTsvParser univRankTsvParser = new UnivRankTsvParser();
        univRankTsvParser.setTsvPath("generated/files/output-univrank06_12_11_2019.tsv");
        univRankTsvParser.readTSV();
        univRankTsvParser.printTSV();

        HibernateUtil hibernateUtil = new HibernateUtil(univRankTsvParser.getUnivRankList());
        hibernateUtil.read();
    }
}
