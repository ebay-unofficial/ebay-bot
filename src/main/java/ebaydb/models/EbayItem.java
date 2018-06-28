package ebaydb.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ebay_items")
public class EbayItem {

    @Id
    @Column(columnDefinition = "VARCHAR(31)")
    private String id;

    private String title;

    @Column(columnDefinition = "LONGTEXT")
    private String description;

    @Column(name = "item_condition")
    private String condition;

    private Boolean auction;

    private Boolean buyNow;

    private Boolean suggestPrice;

    private Boolean sold;

    private Boolean completed;

    @OneToMany(mappedBy = "item")
    private List<EbayItemPrice> prices;

    @ManyToOne
    @JoinColumn(name = "seller")
    private EbaySeller seller;

    public EbayItem() {
        prices = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Boolean isAuction() {
        return auction;
    }

    public void setAuction(Boolean auction) {
        this.auction = auction;
    }

    public Boolean isBuyNow() {
        return buyNow;
    }

    public void setBuyNow(Boolean buyNow) {
        this.buyNow = buyNow;
    }

    public Boolean isSuggestPrice() {
        return suggestPrice;
    }

    public void setSuggestPrice(Boolean suggestPrice) {
        this.suggestPrice = suggestPrice;
    }

    public Boolean isSold() {
        return sold;
    }

    public void setSold(Boolean sold) {
        this.sold = sold;
    }

    public Boolean isCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public List<EbayItemPrice> getPrices() {
        return prices;
    }

    public void setPrices(List<EbayItemPrice> prices) {
        this.prices = prices;
    }

    public void addPrice(EbayItemPrice price) {
        this.prices.add(price);
        price.setItem(this);
    }

    public EbaySeller getSeller() {
        return seller;
    }

    public void setSeller(EbaySeller seller) {
        this.seller = seller;
    }

    @Override
    public String toString() {
        return id + " \'" + title + "\'";
    }
}
