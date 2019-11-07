package main.java;

import main.java.data.TSVConfig;
import main.java.util.crawler.UnivRankCrawler;
import main.java.util.tsv.BasicTsvParser;
import main.java.util.tsv.UnivRankTsvWriter;
import main.java.util.tsv.WordTsvParser;

import java.io.IOException;
import java.util.List;

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
        univRankTsvWriter.writeTSV();

        BasicTsvParser basicTsvParser = new BasicTsvParser();
        basicTsvParser.setTsvPath(TSVConfig.crawledTsvPath);
        basicTsvParser.readTSV();
        basicTsvParser.printTSV();
    }
}

