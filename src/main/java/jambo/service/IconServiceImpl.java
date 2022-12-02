package jambo.service;

import jambo.domain.user.IconShop;
import jambo.dto.IconShopDTO;
import jambo.repository.IconShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class IconServiceImpl implements IconService {

    private final IconShopRepository iconShopRepository;

    @Override
    public List<IconShop> showIconShop() {
        return iconShopRepository.findAll();
    }

    @Override
    public void save(IconShopDTO iconShopDTO) {
        IconShop byName = iconShopRepository.findByName(iconShopDTO.getName());
        if (byName != null) {
            throw new RuntimeException("아이콘 이름이 중복되었습니다. 다른 이름을 사용해주세요");
        }
        IconShop iconShop = iconShopDTO.toEntity();
        iconShopRepository.save(iconShop);
    }
}
