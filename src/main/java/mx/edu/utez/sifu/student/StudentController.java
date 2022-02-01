package mx.edu.utez.sifu.student;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
@Slf4j
public class StudentController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(Model model, Student student) {
        return "index";
    }

    @GetMapping("/view")
    public String view() {
        return "view";
    }

    @PostMapping("/save")
    public String guardar(@Valid Student student, BindingResult result) {
        log.info(student.toString());
        if (result.hasErrors()) {
            return "";
        }
        //userService.save(student);

        return "redirect:" + "";
    }
}