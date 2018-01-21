package ebaydb.models;

import ebaydb.enums.GamePlatform;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "game_products")
public class GameProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private GamePlatform platform;

    public GameProduct(String title, GamePlatform platform) {
        this.title = title;
        this.platform = platform;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public GamePlatform getPlatform() {
        return platform;
    }

    public void setPlatform(GamePlatform platform) {
        this.platform = platform;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameProduct that = (GameProduct) o;
        return Objects.equals(title, that.title) &&
                platform == that.platform;
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, platform);
    }
}
