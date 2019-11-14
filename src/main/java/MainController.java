package main.java;

import main.java.util.analyzer.dao.HibernateUtil;
import main.java.util.crawler.UnivInfoParser;
import main.java.util.crawler.UnivRankParser;
import main.java.util.tsv.UnivRankTsvParser;
import main.java.util.tsv.UnivRankTsvWriter;

import java.io.IOException;

public class MainController {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("======================");

        UnivRankParser univRankParser = new UnivRankParser();
        univRankParser.crawlingSite();
        univRankParser.traverseUnivList();

//        UnivRankTsvWriter univRankTsvWriter = new UnivRankTsvWriter();
//        univRankTsvWriter.setCrawledList(univRankParser.getUnivList());
//        univRankTsvWriter.writeTSVWithTime();
//
//        UnivRankTsvParser univRankTsvParser = new UnivRankTsvParser();
//        univRankTsvParser.setTsvPath(univRankTsvWriter.getTsvPath());
//        univRankTsvParser.readTSV();
//        univRankTsvParser.printTSV();
//
//        HibernateUtil hibernateUtil = new HibernateUtil(univRankTsvParser.getUnivRankList());
//        hibernateUtil.accessDB();

        UnivInfoParser univInfoParser = new UnivInfoParser();
        univInfoParser.setUnivList(univRankParser.getUnivList());
        univInfoParser.crawlingSite();
        univInfoParser.traverseList();
    }
}

