package jambo.service;

import jambo.domain.user.IconShop;
import jambo.dto.IconShopDTO;

import java.util.List;

public interface IconService {
    List<IconShop> showIconShop();

    void save(IconShopDTO iconShopDTO);
}
