package main.java.util.crawler;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import main.java.config.CrawlingConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.regex.Pattern;

public class UnivRankCrawler extends WebCrawler {
    private final static Pattern EXCLUSIONS = Pattern.compile(".*(\\.(css|js|xml|gif|jpg|png|mp3|mp4|zip|gz|pdf))$");
    private final static Logger logger = LoggerFactory.getLogger(UnivRankCrawler.class);
    private CrawlerStatistics stats;

    public UnivRankCrawler(CrawlerStatistics stats) {
        this.stats = stats;
    }

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String urlString = url.getURL().toLowerCase();
        return !EXCLUSIONS.matcher(urlString).matches()
                && urlString.startsWith(CrawlingConfig.univUrl);
    }

    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        logger.info(url);
        stats.incrementProcessedPageCount();

        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String title = htmlParseData.getTitle();
            String text = htmlParseData.getText();
            String html = htmlParseData.getHtml();
            Set<WebURL> links = htmlParseData.getOutgoingUrls();
            stats.incrementTotalLinksCount(links.size());
            String[] split = url.split("/");
            logger.info(split[split.length-1]);
            if(split[split.length-1].matches("[a-zA-z-0-9]")) {
                logger.info("Page with title '{}'", title);
                logger.info("    Text length: {}", text.length());
                logger.info("    HTML length: {}", html.length());

                logger.info("    {} outbound links", links.size());
                try {

                    FileWriter fileWriter = new FileWriter("generated/crawler4j/" + title.replaceAll("[^A-Za-z]", "").toLowerCase() + html.length() + ".html");
                    fileWriter.write(html);
                    fileWriter.flush();
                    fileWriter.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
