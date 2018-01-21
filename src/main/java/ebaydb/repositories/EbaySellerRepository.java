package ebaydb.repositories;

import ebaydb.models.EbaySeller;
import org.springframework.data.repository.CrudRepository;

public interface EbaySellerRepository extends CrudRepository<EbaySeller, String> {
}
