package jambo.domain.user;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IconShop {
    @Id
    @Column(name = "icon_shop_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "icon_shop_id_seq")
    @SequenceGenerator(name = "icon_shop_id_seq", sequenceName = "icon_shop_id_seq")
    private Long id;

    private String name;

    private String description;

    private int price;

    @OneToMany(mappedBy = "iconShop")
    private List<Icon> icons;

    public IconShop(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
