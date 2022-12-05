package jambo.domain.user;

import jambo.domain.user.type.MBTI;
import jambo.repository.IconShopRepository;
import jambo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class UserPurchaseIconDbTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IconShopRepository iconShopRepository;

    @Test
    public void buyIcon() {
        User user = new User("1", "1234"
                , "권규정", "nonoro"
                , "010-1111-1111", MBTI.ENFP
                , new Point(3000, 3000));
        IconShop iconShop = new IconShop("괴물", 1000, 1000);

        iconShopRepository.save(iconShop);
        User saveUser = userRepository.save(user);

        IconShop icon = iconShopRepository.findByName("괴물").get();
        saveUser.buy(icon);

        User findUser = userRepository.findById(saveUser.getId()).get();
        assertThat(findUser.getIcons().get(0).getIconShop()).isEqualTo(icon);
    }
}
