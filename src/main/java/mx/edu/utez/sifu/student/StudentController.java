package mx.edu.utez.sifu.student;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    // @GetMapping("/view")
    // public String view(Model model) {
    //     List<Student> students = studentService.getAll();
    //     model.addAttribute("students", students);
    //     return "view";
    // }

    @GetMapping("/view")
    public String listStudent(
      Model model, 
      @RequestParam("page") Optional<Integer> page, 
      @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(15);

        Page<Student> studentPage = studentService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("studentPage", studentPage);

        int totalPages = studentPage.getTotalPages();
        
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "view";
    }


    @PostMapping("/save")
    public String save(@Valid Student student, BindingResult result) {
        if (result.hasErrors()) {
            return "index";
        }
        studentService.save(student);
        return "redirect:/view";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        studentService.delete(id);
        return "redirect:/view";
    }
}