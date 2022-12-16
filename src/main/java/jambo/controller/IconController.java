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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public String showIconShop(Model model, String keyWord, @PageableDefault(size = 6, direction = Sort.Direction.DESC) Pageable pageable, @AuthenticationPrincipal User securityUser) {
        int state = 1;
        User user = userRepository.findById(securityUser.getId()).get();
        UserResponseDTO userResponseDTO = UserResponseDTO.from(user);
        Page<IconShop> icons = iconService.showIconShop(keyWord, pageable);
        List<Integer> pageNumbers = paginationService.pagination(pageable.getPageNumber(), icons.getTotalPages());
        model.addAttribute("state", state);
        model.addAttribute("icons", icons);
        model.addAttribute("pageNumbers", pageNumbers);
        model.addAttribute("savePath", fileService.getUrlPath());
        model.addAttribute("user", userResponseDTO);
//        }

        return "iconShop/showIconShop";
    }

    @GetMapping("/register")
    public String registerForm() {
        return "iconShop/registerForm";
    }

    @PostMapping("/register")
    public String registerIcon(IconShopDTO iconShopDTO) { // upload는 첨부된 파일의 정보를 가지고있는 객체
        log.debug("업로드 요청");

        String fileName = fileService.upload(iconShopDTO.getFile());

        iconService.save(fileName, iconShopDTO);

//        return "redirect:/icon/shop";
//        return "/admin/adminMain"; // 관리자페이지로 이동
        return "redirect:/icon/adminShop";

    }

    @PostMapping("/buy/{iconId}")
    public String buy(@PathVariable Long iconId, Pageable pageable, @AuthenticationPrincipal User securityUser) {
        log.debug("유저아이디 = {}", securityUser.getId());
        log.debug("구매 아이콘 아이디 = {}", iconId);
        iconService.buy(securityUser, iconId);
        return "redirect:/icon/shop?page=" + pageable.getPageNumber();
    }

    @GetMapping
    public String icon(Model model, @AuthenticationPrincipal User securityUser) {
        User user = userRepository.findById(securityUser.getId()).get();
        UserResponseDTO userResponseDTO = UserResponseDTO.from(user);
        List<IconShop> icons = iconService.getIcons(user);
        log.debug("icons 수 = {}", icons.size());


        model.addAttribute("icons", icons);
        model.addAttribute("savePath", fileService.getUrlPath());
        model.addAttribute("user", userResponseDTO);
        return "iconShop/myIcon";
    }

    @PostMapping("/{iconId}")
    public String icon(@PathVariable Long iconId, Model model, @AuthenticationPrincipal User user) {
        iconService.changeIcon(user.getId(), iconId);
        return "redirect:/icon";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String exception(IllegalArgumentException exception, Model model) {
        model.addAttribute("errorMessage", exception.getMessage());
        return "error/iconError";
    }

    /**
     * 관리자 페이지 아이콘 샵 조회
     * */
    @GetMapping("/adminShop")
    public String showAdminIconShop(Model model, String keyWord, @PageableDefault(size = 5, direction = Sort.Direction.DESC) Pageable pageable) {
        int state = 1;


        Page<IconShop> icons = iconService.showIconShop(keyWord, pageable);
        List<Integer> pageNumbers = paginationService.pagination(pageable.getPageNumber(), icons.getTotalPages());
        model.addAttribute("state", state);
        model.addAttribute("icons", icons);
        model.addAttribute("savePath", fileService.getUrlPath());
        model.addAttribute("pageNumbers", pageNumbers);

        return "admin/iconShop";
    }
}
