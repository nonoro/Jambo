package jambo.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Icon {
    @Id
    @Column(name = "icon_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "icon_seq")
    @SequenceGenerator(name = "icon_seq", sequenceName = "icon_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "icon_shop_id")
    private IconShop iconShop;

}
