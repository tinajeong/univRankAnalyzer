package main.java;

import main.java.util.analyzer.dao.HibernateUtil;
import main.java.util.analyzer.dao.UnivInfoRepo;
import main.java.util.tsv.parser.UnivInfoTsvParser;
import main.java.util.tsv.parser.UnivRankTsvParser;

public class BrowseDBController {
    public static void main(String[] args)
    {
        UnivRankTsvParser univRankTsvParser = new UnivRankTsvParser();
        univRankTsvParser.setTsvPath("/home/migu/git/tsvparser/generated/files/output-univrank06_14_11_2019.tsv");
        univRankTsvParser.readTSV();
        univRankTsvParser.printTSV();

        HibernateUtil hibernateUtil = new HibernateUtil(univRankTsvParser.getUnivRankList());
        hibernateUtil.accessDB();

        UnivInfoTsvParser univInfoTsvParser = new UnivInfoTsvParser();
        univInfoTsvParser.setTsvPath("/home/migu/git/tsvparser/generated/files/output-univInfo06_14_11_2019.tsv");
        univInfoTsvParser.readTSV();
        univInfoTsvParser.printTSV();

        UnivInfoRepo univInfoRepo = new UnivInfoRepo(univInfoTsvParser.getUnivInfoDTOList());
        univInfoRepo.accessDB();
    }
}
