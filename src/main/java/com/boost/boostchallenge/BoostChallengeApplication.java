package com.boost.boostchallenge;

import com.boost.boostchallenge.Service.CrawlerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class BoostChallengeApplication {


    public static void main(String[] args) throws Exception {
        SpringApplication.run(BoostChallengeApplication.class, args);


    }

    @Bean
    CommandLineRunner commandLineRunner(CrawlerService crawlerService) {
        return args -> crawlerService.fetchData();
    }

}
