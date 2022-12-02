package jambo.domain.user;

import lombok.*;

import javax.persistence.*;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Icon {
    @Id
    @Column(name = "icon_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "icon_id_seq")
    @SequenceGenerator(name = "icon_id_seq", sequenceName = "icon_id_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "icon_shop_id")
    private IconShop iconShop;

    public Icon(User user, IconShop iconShop) {
        this.user = user;
        this.iconShop = iconShop;
    }
}
