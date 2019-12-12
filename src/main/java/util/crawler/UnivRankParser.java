package main.java.util.crawler;

import main.java.config.CrawlingConfig;
import main.java.data.dto.UnivRankDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class UnivRankParser implements Crawler {
    ArrayList<UnivRankDTO> univRankDTOS;
    private final static Logger logger = LoggerFactory.getLogger(UnivRankParser.class);

    public UnivRankParser() {
        univRankDTOS = new ArrayList<>();
    }

    public ArrayList<UnivRankDTO> getUnivRankDTOS() {
        return univRankDTOS;
    }

    public void setUnivRankDTOS(ArrayList<UnivRankDTO> univRankDTOS) {
        this.univRankDTOS = univRankDTOS;
    }

    @Override
    public void crawlingSite() throws IOException, InterruptedException {
        for (int i = 1; i <= 10; i++) {
            String pageUrl = CrawlingConfig.univUrl + "?page=" + i;
            crawlingUnivRankPage(pageUrl);
//            sleep(3000);
        }
    }

    public void crawlingUnivRankPage(String url) throws IOException {
        //TODO
        Document doc = null;

        Elements elements = doc.select("#resultsMain .sep");

        for (Element element : elements) {
            UnivRankDTO univRankDTO = new UnivRankDTO();
            //TODO
            univRankDTOS.add(univRankDTO);
        }
    }

    public void traverseUnivList() {
        for (UnivRankDTO univRankDTO : univRankDTOS)
            System.out.println(univRankDTO.getRank() + ":" + univRankDTO.getUnivName() + " in " + univRankDTO.getCountry());
    }
}
