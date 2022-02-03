package mx.edu.utez.sifu.student;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

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
        model.addAttribute("students", students);
        return "view";
    }

    @PostMapping("/save")
    public String guardar(@Valid Student student, BindingResult result) {
        if (result.hasErrors()) {
            return "index";
        }
        studentService.save(student);
        return "redirect:/view";
    }

    @PostMapping("/delete/{id}")
    public String borrar(@PathVariable("id") Integer id) {
        studentService.delete(id);
        return "redirect:/view";
    }
}