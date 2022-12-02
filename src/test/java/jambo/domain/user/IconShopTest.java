package jambo.domain.user;

import jambo.domain.user.Icon;
import jambo.domain.user.IconShop;
import jambo.domain.user.User;
import jambo.dto.IconShopDTO;
import jambo.dto.UserBuyIconDTO;
import jambo.repository.IconRepository;
import jambo.repository.IconShopRepository;
import jambo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Commit
public class IconShopTest {
    @Autowired
    private IconShopRepository iconShopRepository;

    @Autowired
    private IconRepository iconRepository;

    @Autowired
    private UserRepository userRepository;


    @Test
    public void iconShopSaveTest() {
        IconShopDTO iconShopDTO1 = new IconShopDTO("elephant1.gif", "움직이는 코끼리", 2000);
        IconShopDTO iconShopDTO2 = new IconShopDTO("elephant2.gif", "움직이는 코끼리 얼굴", 2000);
        IconShopDTO iconShopDTO3 = new IconShopDTO("elephantE.png", "안움직이는 코끼리", 500);
        IconShop icon1 = iconShopDTO1.toEntity();
        IconShop icon2 = iconShopDTO2.toEntity();
        IconShop icon3 = iconShopDTO3.toEntity();

        iconShopRepository.save(icon1);
        iconShopRepository.save(icon2);
        iconShopRepository.save(icon3);
    }

    @Test
    public void iconShopfindAllTest() {
        iconShopRepository.findAll().forEach(iconList -> System.out.println("iconList = " + iconList));
    }

    @Test
    public void buyUserIcon() {
//        UserBuyIconDTO userBuyIconDTO = new UserBuyIconDTO("kkk@naver.com", "elephant1.gif");
        UserBuyIconDTO userBuyIconDTO = new UserBuyIconDTO("qqq@naver.com", "elephant1.gif");
        User user = userRepository.findByEmail(userBuyIconDTO.getEmail());
        IconShop icon = iconShopRepository.findByName(userBuyIconDTO.getIconName());
        Icon purchasedIcon = new Icon(user, icon);

        purchasedIcon.setUser(user);
        purchasedIcon.setIconShop(icon);

        iconRepository.save(purchasedIcon);
    }
}
