package jambo.service;

import jambo.domain.user.Icon;
import jambo.domain.user.IconShop;
import jambo.domain.user.User;
import jambo.dto.IconShopDTO;
import jambo.repository.IconRepository;
import jambo.repository.IconShopRepository;
import jambo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class IconService {

    private final IconShopRepository iconShopRepository;

    private final UserRepository userRepository;

    private final IconRepository iconRepository;

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

    public void buy(User securityUser, Long iconId) {
        User user = userRepository.findById(securityUser.getId()).get();
        IconShop iconShop = iconShopRepository.findById(iconId).get();

        iconRepository.findByUserAndIconShop(user, iconShop)
                .ifPresent(icon -> {
                            throw new IllegalArgumentException("이미 구매하신 아이콘입니다");
                        });

        user.buy(iconShop);
    }

    public List<IconShop> getIcons(User user) {
        User referenceById = userRepository.getReferenceById(user.getId());
        List<Icon> icons = iconRepository.findAllByUser(referenceById);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>> = " + icons.size());
        return iconShopRepository.findByIconsIn(icons);
    }

    public User changeIcon(Long userId, Long iconId) {
        User user = userRepository.findById(userId).get();
        List<IconShop> icons = iconShopRepository.findByIconsIn(user.getIcons());
        IconShop mainIcon = icons.stream().filter(iconShop -> iconShop.getId().equals(iconId)).findFirst().get();
        user.setMainEmotion(mainIcon.getFileName());
        user.getPoint();

        return user;
    }
}
