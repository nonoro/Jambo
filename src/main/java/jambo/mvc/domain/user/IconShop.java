package jambo.mvc.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IconShop {

    @Id
    @Column(name = "icon_shop_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "icon_shop_seq")
    @SequenceGenerator(name = "icon_shop_seq", sequenceName = "icon_shop_seq", allocationSize = 1)
    private Long id;

    private String name;

    private String description;

    private Long price;


}
