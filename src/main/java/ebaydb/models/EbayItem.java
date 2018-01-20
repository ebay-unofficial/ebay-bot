package ebaydb.models;

import javax.persistence.*;

@Entity
@Table(name = "ebay_items")
public class EbayItem {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
