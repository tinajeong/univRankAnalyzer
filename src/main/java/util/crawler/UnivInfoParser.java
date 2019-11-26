package main.java.util.crawler;

import main.java.config.CrawlingConfig;
import main.java.data.dto.UnivInfoDTO;
import main.java.data.dto.UnivRankDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class UnivInfoParser implements Crawler {
    ArrayList<UnivRankDTO> univRankDTOS;
    ArrayList<UnivInfoDTO> univInfoDTOS;
    private final static Logger logger = LoggerFactory.getLogger(UnivInfoParser.class);

    public UnivInfoParser() {
        univRankDTOS = new ArrayList<>();
        univInfoDTOS = new ArrayList<>();
    }

    public UnivInfoParser(ArrayList<UnivRankDTO> univRankDTOS) {
        this();
        this.univRankDTOS = univRankDTOS;
    }

    @Override
    public void crawlingSite() throws IOException, InterruptedException {
        for (UnivRankDTO univRankDTO : univRankDTOS) {
            crawlingPage(univRankDTO);
            sleep(10);
        }
    }

    private void crawlingPage(UnivRankDTO univRankDTO) throws IOException {
        Document doc = Jsoup.connect(univRankDTO.getUnivInfoHref())
                .header("User-Agent", CrawlingConfig.userAgentMac)
                .timeout(3000)
                .maxBodySize(Integer.MAX_VALUE)
                .get();

        UnivInfoDTO univInfoDTO = new UnivInfoDTO();
        univInfoDTO.setName(univRankDTO.getUnivName());

        Elements elements = doc.select(".directory-data");
        String address = elements.first().select("div").first().text().replaceAll("Address", "").trim();
        logger.debug("address: {}", address);
        String website = elements.eq(1).select("a").attr("href");
        logger.debug("website: {}", website);

        Elements SumElements = doc.select(".maincontent .t-slack").eq(3);
        String summary = null;
        if (!SumElements.select("h2").isEmpty()) {
            summary = SumElements.select("div").first().text().replace("Summary", "").trim();
            logger.debug("summary: {}", summary);
        } else
            logger.debug("summary is empty");

        univInfoDTO.setAddress(address);
        univInfoDTO.setWebsite(website);
        univInfoDTO.setSummary(summary);

        univInfoDTOS.add(univInfoDTO);
    }


    public ArrayList<UnivRankDTO> getUnivRankDTOS() {
        return univRankDTOS;
    }

    public void setUnivRankDTOS(ArrayList<UnivRankDTO> univRankDTOS) {
        this.univRankDTOS = univRankDTOS;
    }

    public ArrayList<UnivInfoDTO> getUnivInfoDTOS() {
        return univInfoDTOS;
    }

    public void setUnivInfoDTOS(ArrayList<UnivInfoDTO> univInfoDTOS) {
        this.univInfoDTOS = univInfoDTOS;
    }

    public void traverseList() {
        univInfoDTOS.forEach(univInfoDTO -> {
            logger.info("{}\n\t-{}\n\t-{}\n\t-{}", univInfoDTO.getName(), univInfoDTO.getAddress(), univInfoDTO.getWebsite(), univInfoDTO.getSummary());
        });
    }
}
