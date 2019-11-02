package main.java.util.crawler;

import main.java.data.UnivRank;

import java.util.ArrayList;

public class UnivRankCrawler implements JSoupCrawler {
    ArrayList<UnivRank> univList;

    public UnivRankCrawler() {
    }

    public UnivRankCrawler(ArrayList<UnivRank> univList) {
        this.univList = univList;
    }

    @Override
    public void crawlingSite() {

    }
}
