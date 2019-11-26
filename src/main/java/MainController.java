package main.java;

import main.java.util.analyzer.dao.HibernateSessionFactory;
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
        univInfoParser.setUnivRankDTOS(univRankParser.getUnivRankDTOS());
        univInfoParser.crawlingSite();
//        univInfoParser.traverseList();

        UnivRankTsvWriter univRankTsvWriter = new UnivRankTsvWriter();
        univRankTsvWriter.setCrawledList(univRankParser.getUnivRankDTOS());
        univRankTsvWriter.writeTSVWithTime();

        UnivRankTsvParser univRankTsvParser = new UnivRankTsvParser();
        univRankTsvParser.setTsvPath(univRankTsvWriter.getTsvPath());
        univRankTsvParser.readTSV();
//        univRankTsvParser.printTSV();

        UnivInfoTsvWriter univInfoTsvWriter = new UnivInfoTsvWriter();
        univInfoTsvWriter.setUnivInfoDTOList(univInfoParser.getUnivInfoDTOS());
        univInfoTsvWriter.writeTSVWithTime();

        UnivInfoTsvParser univInfoTsvParser = new UnivInfoTsvParser();
        univInfoTsvParser.setTsvPath(univInfoTsvWriter.getTsvPath());
        univInfoTsvParser.readTSV();
//        univInfoTsvParser.printTSV();

//        UnivRankDAO univRankDAO = new UnivRankDAO(univRankTsvParser.getUnivRankList());
//        univRankDAO.accessDB();
//        UnivInfoDAO univInfoDAO = new UnivInfoDAO(univInfoTsvParser.getUnivInfoDTOList());
//        univInfoDAO.accessDB();

        HibernateSessionFactory.shutdown();
    }
}

