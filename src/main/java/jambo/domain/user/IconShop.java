package jambo.domain.user;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IconShop {
    @Id
    @Column(name = "icon_shop_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "icon_shop_seq")
    @SequenceGenerator(name = "icon_shop_seq", sequenceName = "icon_shop_seq", allocationSize = 1)
    private Long id;

    private String name;

    private String description;

    private int price;
}
