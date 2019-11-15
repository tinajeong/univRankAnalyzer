package main.java;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import main.java.config.CrawlingConfig;
import main.java.util.crawler.CrawlerStatistics;
import main.java.util.crawler.UnivRankCrawler;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class CrawlingController {

    public static void main(String[] args) throws Exception {
        File crawlStorage = new File("generated/crawler4j");
        CrawlConfig config = new CrawlConfig();

        config.setCrawlStorageFolder(crawlStorage.getAbsolutePath());
        List<Header> headers = Arrays.asList(
                new BasicHeader("Accept", "text/html,text/xml"),
                new BasicHeader("Accept-Language", "en-gb, en-us, en-uk")
        );

        config.setDefaultHeaders(headers);
        config.setMaxDepthOfCrawling(1);
        config.setConnectionTimeout(5000);
        config.setUserAgentString(CrawlingConfig.userAgentMac);
        config.setPolitenessDelay(1000);
        int numCrawlers = 12;

        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        controller.addSeed(CrawlingConfig.univUrl);

        CrawlerStatistics stats = new CrawlerStatistics();
        CrawlController.WebCrawlerFactory<UnivRankCrawler> factory = () -> new UnivRankCrawler(stats);

        controller.start(factory, numCrawlers);
        System.out.printf("Crawled %d pages %n", stats.getProcessedPageCount());
        System.out.printf("Total Number of outbound links = %d %n", stats.getTotalLinksCount());
    }
}
