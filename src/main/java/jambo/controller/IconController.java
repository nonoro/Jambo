package jambo.controller;

import jambo.domain.user.IconShop;
import jambo.dto.IconShopDTO;
import jambo.service.IconService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/icon")
@RequiredArgsConstructor
public class IconController {

    private final IconService iconService;

    @RequestMapping("/iconShop")
    public String showIconShop(Model model) {
        List<IconShop> iconShops = iconService.showIconShop();
        model.addAttribute("iconShops", iconShops);

        return "/showIconShop";
    }

    @RequestMapping("/registerIcon")
    public String saveIcon(IconShopDTO iconShopDTO) {
        iconService.save(iconShopDTO);
        return "redirect:/icon/iconShop";
    }
}
