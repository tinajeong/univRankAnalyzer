package main.java;

import main.java.util.analyzer.dao.UnivRankRepo;
import main.java.util.analyzer.dao.UnivInfoRepo;
import main.java.util.crawler.UnivInfoParser;
import main.java.util.crawler.UnivRankParser;
import main.java.util.tsv.parser.UnivInfoTsvParser;
import main.java.util.tsv.parser.UnivRankTsvParser;
import main.java.util.tsv.writer.UnivInfoTsvWriter;
import main.java.util.tsv.writer.UnivRankTsvWriter;

import java.io.IOException;

public class MainController {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("======================");

        UnivRankParser univRankParser = new UnivRankParser();
        univRankParser.crawlingSite();
        univRankParser.traverseUnivList();

        UnivInfoParser univInfoParser = new UnivInfoParser();
        univInfoParser.setUnivList(univRankParser.getUnivList());
        univInfoParser.crawlingSite();
//        univInfoParser.traverseList();

        UnivRankTsvWriter univRankTsvWriter = new UnivRankTsvWriter();
        univRankTsvWriter.setCrawledList(univRankParser.getUnivList());
        univRankTsvWriter.writeTSVWithTime();

        UnivRankTsvParser univRankTsvParser = new UnivRankTsvParser();
        univRankTsvParser.setTsvPath(univRankTsvWriter.getTsvPath());
        univRankTsvParser.readTSV();
//        univRankTsvParser.printTSV();

        UnivInfoTsvWriter univInfoTsvWriter = new UnivInfoTsvWriter();
        univInfoTsvWriter.setUnivInfoDTOList(univInfoParser.getUnivInfoList());
        univInfoTsvWriter.writeTSVWithTime();

        UnivInfoTsvParser univInfoTsvParser = new UnivInfoTsvParser();
        univInfoTsvParser.setTsvPath(univInfoTsvWriter.getTsvPath());
        univInfoTsvParser.readTSV();
//        univInfoTsvParser.printTSV();

        UnivRankRepo univRankRepo = new UnivRankRepo(univRankTsvParser.getUnivRankList());
        univRankRepo.accessDB();
        UnivInfoRepo univInfoRepo = new UnivInfoRepo(univInfoTsvParser.getUnivInfoDTOList());
        univInfoRepo.accessDB();
    }
}

