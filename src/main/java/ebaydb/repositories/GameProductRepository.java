package ebaydb.repositories;

import ebaydb.models.GameProduct;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GameProductRepository extends CrudRepository<GameProduct, String> {

    public List<GameProduct> findAll();
}
