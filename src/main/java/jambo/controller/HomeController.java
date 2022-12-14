package jambo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/error/403")
    public String error() {
        return "error/403";
    }

    @GetMapping("/whoWeAre")
    public String openWhoWeAre(){
        return "team/WhoWeAre";
    }
}
