package com.boost.boostchallenge;

import com.boost.boostchallenge.Service.CrawlerService;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.regex.Pattern;

@Component
public class BoostCrawler extends WebCrawler {

    private CrawlerService crawlerService;

    public BoostCrawler(CrawlerService crawlerService) {
        this.crawlerService = crawlerService;
    }

    private static final Pattern FILTERS = Pattern.compile(
            ".*(\\.(css|js|mid|mp2|mp3|mp4|json|wav|avi|flv|mov|mpeg|ram|m4v|pdf" +
                    "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String urlString = url.getURL().toLowerCase();
        return !FILTERS.matcher(urlString).matches()
                && urlString.startsWith("https://monzo.com/");
    }

    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String title = htmlParseData.getTitle();
            String text = htmlParseData.getText();
            String html = htmlParseData.getHtml();
            Set<WebURL> links = htmlParseData.getOutgoingUrls();

            System.out.printf("Page with title '%s' %n", title);
            System.out.printf("    Text length: %d %n", text.length());
            System.out.printf("    HTML length: %d %n", html.length());
            System.out.printf("    %d outbound links %n", links.size());


            links.forEach((WebURL webUrl) -> {
                System.out.println(webUrl);

            });
        }
    }
}
