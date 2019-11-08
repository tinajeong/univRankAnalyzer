package main.java;

import main.java.util.analyzer.dao.HibernateUtil;
import main.java.util.crawler.UnivRankCrawler;
import main.java.util.tsv.BasicTsvParser;
import main.java.util.tsv.UnivRankTsvWriter;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("======================");
//        new IOUtil().testIOUtil();

//        BasicTsvParser basicTsvParser = new BasicTsvParser();
//        basicTsvParser.readTSV();
//        basicTsvParser.printTSV();

//        WordTsvParser wordTsvParser = new WordTsvParser();
//        wordTsvParser.readTSV();
//        wordTsvParser.printTSV();

        UnivRankCrawler univRankCrawler = new UnivRankCrawler();
        univRankCrawler.crawlingSite();
        univRankCrawler.traverseUnivList();

        UnivRankTsvWriter univRankTsvWriter = new UnivRankTsvWriter();
        univRankTsvWriter.setCrawledList(univRankCrawler.getUnivList());
        univRankTsvWriter.writeTSVWithTime();

        BasicTsvParser basicTsvParser = new BasicTsvParser();
        basicTsvParser.setTsvPath(univRankTsvWriter.getTsvPath());
        basicTsvParser.readTSV();
        basicTsvParser.printTSV();

        HibernateUtil hibernateUtil = new HibernateUtil(univRankCrawler.getUnivList());
        hibernateUtil.accessDB();
    }
}

