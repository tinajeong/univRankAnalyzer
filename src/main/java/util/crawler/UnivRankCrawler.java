package main.java.util.crawler;

import main.java.config.CrawlingConfig;
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
        for (int i = 1; i <= 10; i++) {
            String pageUrl = CrawlingConfig.univUrl + "&page=" + i;
//            System.out.println(pageUrl);
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
            Long rank = Long.parseLong(element.child(1).text().substring(1).replaceAll("[^0-9]",""));
            univRankDTO.setRank(rank);
            univRankDTO.setUnivName(element.child(2).select(".h-taut a").text());
            univRankDTO.setCountry(element.child(2).select(".t-taut span").first().text());
            univList.add(univRankDTO);
//            System.out.println(element.child(1).text());
//            System.out.println(element.child(2).select(".h-taut a").text());
//            System.out.println(element.child(2).select(".t-taut span").first().text());
        }
    }

    public void traverseUnivList() {
        for (UnivRankDTO univRankDTO : univList)
            System.out.println(univRankDTO.getRank() + ":" + univRankDTO.getUnivName() + " in " + univRankDTO.getCountry());
    }
}
