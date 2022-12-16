package jambo.domain.user;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class)
public class IconShop {
    @Id
    @Column(name = "icon_shop_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "icon_shop_id_seq")
    @SequenceGenerator(name = "icon_shop_id_seq", sequenceName = "icon_shop_id_seq")
    private Long id;

    private String name;

    private String fileName;

    private int price;

    private int quantity;

    @CreatedDate
    private LocalDateTime saveDate;

    @OneToMany(mappedBy = "iconShop")
    private List<Icon> icons;

    public IconShop(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public IconShop(Long id, String name, int price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public void decreaseQty() {
        quantity -= 1;
    }
}
