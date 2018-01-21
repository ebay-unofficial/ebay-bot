package ebaydb.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ebay_seller_histories")
public class EbaySellerHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "seller")
    private EbaySeller seller;

    private Date timestamp;

    private int stars;

    private double feedback;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EbaySeller getSeller() {
        return seller;
    }

    public void setSeller(EbaySeller seller) {
        this.seller = seller;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public double getFeedback() {
        return feedback;
    }

    public void setFeedback(double feedback) {
        this.feedback = feedback;
    }
}
