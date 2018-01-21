package ebaydb.repositories;

import ebaydb.models.EbayItemPrice;
import org.springframework.data.repository.CrudRepository;

public interface EbayItemPriceRepository extends CrudRepository<EbayItemPrice, Long> {
}
