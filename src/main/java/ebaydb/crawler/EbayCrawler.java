package ebaydb.crawler;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import ebaydb.models.EbayItem;
import ebaydb.models.EbayItemPrice;
import ebaydb.models.EbaySeller;
import ebaydb.models.EbaySellerHistory;
import ebaydb.repositories.EbayItemPriceRepository;
import ebaydb.repositories.EbayItemRepository;
import ebaydb.services.HttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EbayCrawler {

    @Autowired
    private HttpService httpService;
    @Autowired
    private EbayItemRepository itemRepository;
    @Autowired
    private EbayItemPriceRepository itemPriceRepository;

    public EbayCrawler() {

    }

    public void crawlSoldNewest(String title) {
        parseItems("/search?s=" + title + "&sold=true&location=global&order=newly");
    }

    public void crawlSoldLowest(String title) {
        // TODO
    }

    public void crawlBuyNowLowest(String title) {
        // worldwide
        // TODO
    }

    public void crawlAuctionEndingSoon(String query) {
        // worldwide
        // TODO
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
        }
    }

}
