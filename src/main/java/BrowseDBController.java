package main.java;

import main.java.util.analyzer.dao.UnivRankRepo;
import main.java.util.analyzer.dao.UnivInfoRepo;
import main.java.util.tsv.parser.UnivInfoTsvParser;
import main.java.util.tsv.parser.UnivRankTsvParser;

public class BrowseDBController {
    public static void main(String[] args) {
        //flow : store data from tsv
        UnivRankTsvParser univRankTsvParser = new UnivRankTsvParser();
        univRankTsvParser.setTsvPath(args[0]);
        univRankTsvParser.readTSV();
        univRankTsvParser.printTSV();

        UnivRankRepo univRankRepo = new UnivRankRepo(univRankTsvParser.getUnivRankList());
        univRankRepo.accessDB();

        UnivInfoTsvParser univInfoTsvParser = new UnivInfoTsvParser();
        univInfoTsvParser.setTsvPath(args[1]);
        univInfoTsvParser.readTSV();
        univInfoTsvParser.printTSV();

        UnivInfoRepo univInfoRepo = new UnivInfoRepo(univInfoTsvParser.getUnivInfoDTOList());
        univInfoRepo.accessDB();

        //flow : read only
//        UnivRankRepo univRankRepo = new UnivRankRepo();
//        univRankRepo.read();
//        UnivInfoRepo univInfoRepo = new UnivInfoRepo();
//        univInfoRepo.read();
    }
}
