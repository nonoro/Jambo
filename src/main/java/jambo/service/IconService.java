package jambo.service;

import jambo.domain.user.IconShop;
import jambo.repository.IconShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class IconService {

    private final IconShopRepository iconShopRepository;

    public Page<IconShop> showIconShop(String keyWord, Pageable page) {
        if (keyWord == null) {
            return iconShopRepository.findAllByOrderBySaveDateDesc(page);
        }
        return iconShopRepository.searchByName(keyWord, page);
    }

    public void save(IconShop iconShop) {
        IconShop byName = iconShopRepository.findByName(iconShop.getName());
        IconShop byFileName = iconShopRepository.findByFileName(iconShop.getFileName());

        if (byName != null) {
            throw new RuntimeException("아이콘 이름이 중복되었습니다. 다른 이름을 사용해주세요");
        }

        if (byFileName != null) {
            throw new RuntimeException("이미 존재하는 아이콘 입니다");
        }

        iconShopRepository.save(iconShop);
    }

}
