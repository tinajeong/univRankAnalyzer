package main.java.util.crawler;

import main.java.data.CrawlingConfig;
import main.java.data.UnivRankDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class UnivRankCrawler implements Crawler {
    ArrayList<UnivRankDTO> univList;

    public UnivRankCrawler() {
        univList = new ArrayList<>();
    }

    public ArrayList<UnivRankDTO> getUnivList() {
        return univList;
    }

    public void setUnivList(ArrayList<UnivRankDTO> univList) {
        this.univList = univList;
    }

    @Override
    public void crawlingSite() throws IOException, InterruptedException {
    }

    public void crawlingUnivRankPage(String url) throws IOException {
    }

    public void traverseUnivList() {
        for (UnivRankDTO univRankDTO : univList)
            System.out.println(univRankDTO.getRank() + ":" + univRankDTO.getUnivName() + " in " + univRankDTO.getCountry());
    }
}
