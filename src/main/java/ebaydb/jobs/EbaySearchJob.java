package ebaydb.jobs;

import ebaydb.crawler.EbayCrawler;
import ebaydb.models.GameProduct;
import ebaydb.repositories.GameProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EbaySearchJob {
    private static final Logger LOGGER = LoggerFactory.getLogger(EbaySearchJob.class);

    @Autowired
    private GameProductRepository gameProductRepo;

    @Autowired
    private EbayCrawler ebayCrawler;

    @Scheduled(fixedRate = 1000 * 60 * 60)
    public void searchGames() {
        List<GameProduct> games = gameProductRepo.findAll();
        for (int i = 0; i < games.size(); i++) {
            GameProduct game = games.get(i);
            String query = game.getTitle() + " " + game.getPlatform().name;

            LOGGER.info("");
            LOGGER.info("Searching: {}", query);

            ebayCrawler.crawlBuyNowLowest(query);
        }
    }

}
