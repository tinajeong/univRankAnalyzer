package main.java;

import main.java.util.crawler.UnivRankCrawler;
import main.java.util.tsv.UnivRankTsvWriter;
import main.java.util.tsv.WordTsvParser;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
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

        univRankTsvWriter.writeTSV(univRankCrawler.getUnivList());
    }
}

