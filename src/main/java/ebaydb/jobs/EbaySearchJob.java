package ebaydb.jobs;

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

    @Scheduled
    public void searchGames() {
        List<GameProduct> games = gameProductRepo.findAll();
        for (GameProduct game : games) {

        }
    }

}
