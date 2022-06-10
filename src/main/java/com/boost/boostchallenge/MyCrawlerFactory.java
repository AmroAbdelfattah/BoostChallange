package com.boost.boostchallenge;

import com.boost.boostchallenge.Service.CrawlerServiceImpl;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.WebCrawler;

public class MyCrawlerFactory implements CrawlController.WebCrawlerFactory {

    @Override
    public WebCrawler newInstance() throws Exception {
        return new BoostCrawler(new CrawlerServiceImpl());
    }
}