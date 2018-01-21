package ebaydb.jobs;

import ebaydb.crawler.EbayCrawler;
import ebaydb.models.GameProduct;
import ebaydb.repositories.GameProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EbaySearchJob {

    @Autowired
    private GameProductRepository gameProductRepo;

    @Autowired
    private EbayCrawler ebayCrawler;

    @Scheduled(fixedRate = 1000 * 60 * 60)
    public void searchGames() {
        List<GameProduct> games = gameProductRepo.findAll();
        for (int i = 0; i < 10; i++) {
            GameProduct game = games.get(i);
            ebayCrawler.crawlBuyNowLowest(game.getTitle() + " " + game.getPlatform().name);
        }
    }

}
