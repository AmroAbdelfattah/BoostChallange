package com.boost.boostchallenge.Service;

import com.boost.boostchallenge.MyCrawlerFactory;
import com.boost.boostchallenge.Service.CrawlerService;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class CrawlerServiceImpl implements CrawlerService {


    @Override
    public void fetchData() throws Exception {
        File crawlStorage = new File("src/test/resources/crawler4j");
        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorage.getAbsolutePath());
        config.setMaxDepthOfCrawling(1);

        int numCrawlers = 1;

        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        controller.addSeed("https://monzo.com/");

        MyCrawlerFactory factory = new MyCrawlerFactory();

        controller.start(factory, numCrawlers);
    }
}
