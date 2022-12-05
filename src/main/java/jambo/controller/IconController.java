package jambo.controller;

import jambo.domain.user.IconShop;
import jambo.domain.user.User;
import jambo.dto.IconShopDTO;
import jambo.dto.UserResponseDTO;
import jambo.repository.UserRepository;
import jambo.service.FileService;
import jambo.service.IconService;
import jambo.service.PaginationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/icon")
@RequiredArgsConstructor
@Slf4j
public class IconController {
    private final IconService iconService;

    private final FileService fileService;

    private final PaginationService paginationService;

    private final UserRepository userRepository;

    @GetMapping("/shop")
    public String showIconShop(Model model, String keyWord, @PageableDefault(size = 3, direction = Sort.Direction.DESC) Pageable pageable) {
        Long userId = 1L;
        User user = userRepository.findById(userId).get();
        UserResponseDTO userResponseDTO = UserResponseDTO.from(user);
        Page<IconShop> icons = iconService.showIconShop(keyWord, pageable);
        List<Integer> pageNumbers = paginationService.pagination(pageable.getPageNumber(), icons.getTotalPages());
        model.addAttribute("icons", icons);
        model.addAttribute("pageNumbers", pageNumbers);
        model.addAttribute("savePath", fileService.getUrlPath());
        model.addAttribute("user", userResponseDTO);
        return "icon/showIconShop";
    }

    @GetMapping("/register")
    public String registerForm() {
        return "icon/registerForm";
    }

    @PostMapping("/register")
    public String registerIcon(IconShopDTO iconShopDTO) { // upload는 첨부된 파일의 정보를 가지고있는 객체
        log.debug("업로드 요청");

        String fileName = fileService.upload(iconShopDTO.getFile());

        iconService.save(fileName, iconShopDTO);

        return "redirect:/icon/shop";
    }

    @PostMapping("/buy/{iconId}")
    public String buy(@PathVariable Long iconId, Pageable pageable) {
        Long userId = 1L;
        log.debug("구매 아이콘 아이디 = {}", iconId);
        iconService.buy(userId, iconId);
        return "redirect:/icon/shop?page=" + pageable.getPageNumber();
    }

    @GetMapping
    public String icon(Model model) {
        Long userId = 1L;
        User user = userRepository.findById(userId).get();
        UserResponseDTO userResponseDTO = UserResponseDTO.from(user);
        List<IconShop> icons = iconService.getIcons(userId);
        log.debug("icons 수 = {}", icons.size());


        model.addAttribute("icons", icons);
        model.addAttribute("savePath", fileService.getUrlPath());
        model.addAttribute("user", userResponseDTO);
        return "icon/myIcon";
    }

    @PostMapping("/{iconId}")
    public String icon(@PathVariable Long iconId, Model model) {
        Long userId = 1L;
        User user = iconService.changeIcon(userId, iconId);
        return "redirect:/icon";
    }
}
