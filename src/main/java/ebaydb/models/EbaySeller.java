package ebaydb.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "ebay_sellers")
public class EbaySeller {

    @Id
    @Column(columnDefinition = "VARCHAR(31)")
    private String name;

    @OneToMany(mappedBy = "seller")
    private List<EbaySellerHistory> history;

    @OneToMany(mappedBy = "seller")
    private List<EbayItem> items;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EbaySellerHistory> getHistory() {
        return history;
    }

    public void setHistory(List<EbaySellerHistory> history) {
        this.history = history;
    }

    public List<EbayItem> getItems() {
        return items;
    }

    public void setItems(List<EbayItem> items) {
        this.items = items;
    }
}
