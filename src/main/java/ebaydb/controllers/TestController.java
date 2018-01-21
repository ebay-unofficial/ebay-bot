package ebaydb.controllers;

import ebaydb.crawler.EbayCrawler;
import ebaydb.models.EbayItem;
import ebaydb.repositories.EbayItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@RestController
public class TestController {

    @Autowired
    private EbayItemRepository repo;

    @Autowired
    private EbayCrawler crawler;

    @RequestMapping("/items")
    public Iterable<EbayItem> getAll() {
        return repo.findAll();
    }

    @RequestMapping("search")
    public void search(@RequestParam("s") String term) {
        crawler.crawlSoldNewest(term);
    }
}
