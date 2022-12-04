package jambo.controller;

import jambo.domain.user.IconShop;
import jambo.dto.IconShopDTO;
import jambo.service.FileService;
import jambo.service.IconService;
import jambo.service.PaginationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/icon")
@RequiredArgsConstructor
@Slf4j
public class IconController {
    private final IconService iconService;

    private final FileService fileService;

    private final PaginationService paginationService;

    @RequestMapping("/shop")
    public String showIconShop(Model model, String keyWord, @PageableDefault(size = 3, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<IconShop> icons = iconService.showIconShop(keyWord, pageable);
        List<Integer> pageNumbers = paginationService.pagination(pageable.getPageNumber(), icons.getTotalPages());
        model.addAttribute("icons", icons);
        model.addAttribute("pageNumbers", pageNumbers);
        model.addAttribute("savePath", fileService.getUrlPath());

        return "icon/showIconShop";
    }

    @GetMapping("/register")
    public String registerForm() {
        return "icon/registerForm";
    }

    @PostMapping("/register")
    public String registerIcon(IconShopDTO iconShopDTO) { // upload는 첨부된 파일의 정보를 가지고있는 객체
        log.debug("업로드 요청");

        String fileName = fileService.saveFile(iconShopDTO.getFile());

        IconShop iconShop = iconShopDTO.toEntity();
        iconShop.setFileName(fileName);
        iconService.save(iconShop);

        return "redirect:shop";
    }
}
