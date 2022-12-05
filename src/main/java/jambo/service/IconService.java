package jambo.service;

import jambo.domain.user.IconShop;
import jambo.dto.IconShopDTO;
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

    public void save(String fileName, IconShopDTO iconShopDTO) {
        iconShopRepository.findByName(iconShopDTO.getName())
                .ifPresent(i -> {
                    throw new IllegalStateException();
                });

        IconShop iconShop = iconShopDTO.toEntity();
        iconShop.setFileName(fileName);

        iconShopRepository.save(iconShop);
    }

}
