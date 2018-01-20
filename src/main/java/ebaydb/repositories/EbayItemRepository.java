package ebaydb.repositories;

import ebaydb.models.EbayItem;
import org.springframework.data.repository.CrudRepository;

public interface EbayItemRepository extends CrudRepository<EbayItem, Long> {
}
