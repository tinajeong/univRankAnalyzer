package main.java.util.crawler;

import main.java.config.CrawlingConfig;
import main.java.data.UnivRankDTO;
import main.java.util.analyzer.dao.HibernateUtil;
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
    ArrayList<UnivRankDTO> univList;
    private final static Logger logger = LoggerFactory.getLogger(UnivRankParser.class);

    public UnivRankParser() {
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
        for (int i = 1; i <= 10; i++) {
            String pageUrl = CrawlingConfig.univUrl + "?page=" + i;
            crawlingUnivRankPage(pageUrl);
//            sleep(3000);
        }
    }

    public void crawlingUnivRankPage(String url) throws IOException {
        Document doc = Jsoup.connect(url)
                .header("User-Agent", "Mozilla/5.0")
                .timeout(5000)
                .get();

        Elements elements = doc.select("#resultsMain .sep");
//        System.out.println(elements);

        for (Element element : elements) {
            UnivRankDTO univRankDTO = new UnivRankDTO();
            Long rank = Long.parseLong(element.child(1).text().substring(1).replaceAll("[^0-9]", ""));
            univRankDTO.setRank(rank);
            String univName = element.child(2).select(".h-taut a").text().replaceAll("[^A-Za-z ]"," ").trim();
            univRankDTO.setUnivName(univName);
            univRankDTO.setCountry(element.child(2).select(".t-taut span").first().text());
            univRankDTO.setUnivInfoHref(element.child(2).select(".h-taut a").attr("href"));
            univList.add(univRankDTO);
        }
    }

    public void traverseUnivList() {
        for (UnivRankDTO univRankDTO : univList)
            System.out.println(univRankDTO.getRank() + ":" + univRankDTO.getUnivName() + " in " + univRankDTO.getCountry());
    }
}
