package mx.edu.utez.sifu.student;

import lombok.extern.slf4j.Slf4j;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
@Slf4j
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public String index(Model model, Student student) {
        return "index";
    }

    @GetMapping("/view")
    public String view(Model model) {
        List<Student> students = studentService.getAll();
        model.addAttribute("students",students);
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