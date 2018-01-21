package ebaydb.services;

import ebaydb.enums.GamePlatform;
import ebaydb.models.GameProduct;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CSVParser {

    public List<GameProduct> parseGameList(Resource path, GamePlatform platform) {
        List<GameProduct> gameList = new ArrayList<>();
        try {
            String content = IOUtils.toString(path.getInputStream());
            String[] titles = content.split("[|]");
            for (int i = 0; i < titles.length; i++) {
                String title = titles[i];
                GameProduct gameProduct = new GameProduct(title, platform);
                gameList.add(gameProduct);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gameList;
    }
}
