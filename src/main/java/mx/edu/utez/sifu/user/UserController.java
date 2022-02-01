package mx.edu.utez.sifu.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
public class UserController {
    @GetMapping("/")
    public String index(Model model, User user) {
        user.setIsWorking(true);
        return "index";
    }


    @GetMapping("/view")
    public String view() {
        return "view";
    }
}
