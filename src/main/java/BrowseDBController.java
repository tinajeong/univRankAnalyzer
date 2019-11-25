package main.java;

import main.java.util.analyzer.dao.UnivRankDAO;
import main.java.util.analyzer.dao.UnivInfoDAO;
import main.java.util.tsv.parser.UnivInfoTsvParser;
import main.java.util.tsv.parser.UnivRankTsvParser;

public class BrowseDBController {
    public static void main(String[] args) {
        ///home/migu/git/tsvparser/generated/files/output-univrank06_14_11_2019.tsv /home/migu/git/tsvparser/generated/files/output-univInfo06_14_11_2019.tsv 2
        System.out.println("enter two tsv paths if you want to update DB!\n\tflow 1: read only\n\tflow 2: store and update data from tsv");
        if (args.length < 2) {
            //flow 1: read only
            UnivRankDAO univRankRepo = new UnivRankDAO();
            univRankRepo.read();
            UnivInfoDAO univInfoRepo = new UnivInfoDAO();
            univInfoRepo.read();
        }
        else {
            //flow 2: store and update data from tsv
            UnivRankTsvParser univRankTsvParser = new UnivRankTsvParser();
            univRankTsvParser.setTsvPath(args[0]);
            univRankTsvParser.readTSV();
            univRankTsvParser.printTSV();

            UnivRankDAO univRankDAO = new UnivRankDAO(univRankTsvParser.getUnivRankList());
            univRankDAO.accessDB();

            UnivInfoTsvParser univInfoTsvParser = new UnivInfoTsvParser();
            univInfoTsvParser.setTsvPath(args[1]);
            univInfoTsvParser.readTSV();
            univInfoTsvParser.printTSV();

            UnivInfoDAO univInfoDAO = new UnivInfoDAO(univInfoTsvParser.getUnivInfoDTOList());
            univInfoDAO.accessDB();
        }
    }

}
