package ebaydb.crawler;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import ebaydb.models.EbayItem;
import ebaydb.models.EbayItemPrice;
import ebaydb.repositories.EbayItemPriceRepository;
import ebaydb.repositories.EbayItemRepository;
import ebaydb.services.HttpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Component
public class EbayCrawler {
    private static final Logger LOGGER = LoggerFactory.getLogger(EbayCrawler.class);

    @Autowired
    private HttpService httpService;
    @Autowired
    private EbayItemRepository itemRepository;
    @Autowired
    private EbayItemPriceRepository itemPriceRepository;


    public EbayCrawler() {

    }

    public void crawlSoldNewest(String query) {
        try {
            parseItems("/search?s=" + URLEncoder.encode(query, "UTF-8") + "&sold=true&location=global&order=newly");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void crawlSoldLowest(String query) {
        try {
            parseItems("/search?s=" + URLEncoder.encode(query, "UTF-8") + "&sold=true&location=global&order=lowest");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void crawlBuyNowLowest(String query) {
        try {
            parseItems("/search?s=" + URLEncoder.encode(query, "UTF-8") + "&buynow=true&location=global&order=lowest");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void crawlAuctionEndingSoon(String query) {
        try {
            parseItems("/search?s=" + URLEncoder.encode(query, "UTF-8") + "&auction=true&location=global&order=soonest");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void parseItems(String url) {
        parseItems(httpService.httpGetJson(url).get("items").getAsJsonArray());
    }

    public void parseItems(JsonArray apiItems) {
        for (int i=0; i<apiItems.size(); i++) {
            JsonObject apiItem = apiItems.get(i).getAsJsonObject();

            EbayItemPrice price = new EbayItemPrice();
            price.setPrice(apiItem.get("price").getAsDouble());
            price.setShipping(apiItem.get("shipping").getAsDouble());
            price.setCurrency(apiItem.get("currency").getAsString());

            EbayItem item = new EbayItem();
            item.setId(apiItem.get("id").getAsString());
            item.setTitle(apiItem.get("title").getAsString());
            item.setAuction(apiItem.get("auction").getAsBoolean());
            item.setBuyNow(apiItem.get("buyNow").getAsBoolean());
            item.setSuggestPrice(apiItem.get("suggestPrice").getAsBoolean());
            item.setCondition(apiItem.get("condition").getAsString());
            item.addPrice(price);

            itemRepository.save(item);
            itemPriceRepository.save(price);

            LOGGER.info("Item {}: {} \"{}\" stored", i, item.getId(), item.getTitle());
        }
    }

}
