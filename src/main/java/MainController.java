package main.java;

import main.java.config.TSVConfig;
import main.java.util.analyzer.dao.HibernateUtil;
import main.java.util.crawler.UnivRankCrawler;
import main.java.util.tsv.BasicTsvParser;
import main.java.util.tsv.UnivRankTsvParser;
import main.java.util.tsv.UnivRankTsvWriter;

import java.io.IOException;

public class MainController {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("======================");

        UnivRankCrawler univRankCrawler = new UnivRankCrawler();
        univRankCrawler.crawlingSite();
        univRankCrawler.traverseUnivList();

        UnivRankTsvWriter univRankTsvWriter = new UnivRankTsvWriter();
        univRankTsvWriter.setCrawledList(univRankCrawler.getUnivList());
        univRankTsvWriter.writeTSVWithTime();

        UnivRankTsvParser univRankTsvParser = new UnivRankTsvParser();
        univRankTsvParser.setTsvPath(univRankTsvWriter.getTsvPath());
        univRankTsvParser.readTSV();
        univRankTsvParser.printTSV();

        HibernateUtil hibernateUtil = new HibernateUtil(univRankTsvParser.getUnivRankList());
        hibernateUtil.read();
    }
}

