package jambo.domain.user;

import jambo.domain.user.type.MBTI;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


public class UserPurchaseIconTest {

    @Test
    public void purchaseByIcon() {
        User user = new User("1", "1234"
                , "권규정", "nonoro"
                , "010-1111-1111", MBTI.ENFP
                , new Point(1000, 1000));

        IconShop icon = new IconShop(1L, "코끼리", 300, 10);

        user.buy(icon);

        assertAll(
                () -> assertThat(user.getAvailablePoint()).isEqualTo(700),
                () -> assertThat(icon.getQuantity()).isEqualTo(9),
                () -> assertThat(user.getIcons()).hasSize(1));
    }
}
