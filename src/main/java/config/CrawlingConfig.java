package main.java.config;

public class CrawlingConfig {
    public final static String univUrl = "https://www.usnews.com/education/best-global-universities/computer-science";
    //https://www.whatismybrowser.com/detect/what-is-my-user-agent
    public final static String userAgentDefault = "Mozilla/5.0";
    public final static String userAgentLinux = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) snap Chromium/78.0.3904.97 Chrome/78.0.3904.97 Safari/537.36";
    public final static String userAgentMac = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.75 Safari/537.36";
    public final static String userAgentWindow = "";
    public final static String crawler4jUserAgent = "crawler4j (http://code.google.com/p/crawler4j/)";
    public final static int pageNum = 10;

    private static class Lazyholder {
        public static final CrawlingConfig INSTANCE = new CrawlingConfig();
    }

    public static CrawlingConfig getInstance() {
        return Lazyholder.INSTANCE;
    }


}
