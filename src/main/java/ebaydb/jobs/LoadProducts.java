package ebaydb.jobs;

import ebaydb.enums.GamePlatform;
import ebaydb.models.GameProduct;
import ebaydb.repositories.GameProductRepository;
import ebaydb.services.CSVParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Loads game lists from resources into the database uppon Application start
 */
@Component
public class LoadProducts implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private CSVParser csvParser;

    @Autowired
    private GameProductRepository gameProductRepo;

    @Value(value = "classpath:products/gamecube_en.csv")
    private Resource gamecubeCsv;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        insertGames();
    }

    @Transactional
    void insertGames() {
        List<GameProduct> dbGameList = gameProductRepo.findAll();
        List<GameProduct> gameList = csvParser.parseGameList(gamecubeCsv, GamePlatform.GAMECUBE);
        gameList.removeAll(dbGameList);
        for (GameProduct game : gameList) {
            gameProductRepo.save(game);
        }
    }
}
