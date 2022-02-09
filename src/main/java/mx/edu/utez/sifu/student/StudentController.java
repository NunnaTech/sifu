package mx.edu.utez.sifu.student;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.tags.BindErrorsTag;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Path.Node;

@Controller
@RequestMapping("/")
@Slf4j
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentValidators validators;

    private int currentPage = 1;
    private int pageSize = 15;

    @GetMapping("/")
    public String index(Model model, Student student) {
        model = fillSelects(model);
        return "index";
    }

    @GetMapping("/view")
    public String listStudent(
      Model model, 
      @RequestParam("page") Optional<Integer> page, 
      @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(this.currentPage);
        int pageSize = size.orElse(this.pageSize);

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
    public String save(Model model,@Valid Student student, BindingResult result) {
        if (result.hasErrors()) {
            return "index";
        }
        try {
            studentService.save(student);
        } catch (ConstraintViolationException e) {
            List<String> fields = new ArrayList<>();
            ConstraintViolation<?> violation = e.getConstraintViolations().iterator().next();
        
            for (Node a :violation.getPropertyPath())
                fields.add(a.getName());
        
            for (String field : fields) 
                result.rejectValue(field, "messageCode", "Valor no válido");

            model = fillSelects(model);

            return "index";
        }
        return "redirect:/view";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        studentService.delete(id);
        return "redirect:/view";
    }

    private Model fillSelects(Model model){
        model.addAttribute("genders",validators.getGenders());
        model.addAttribute("regions",validators.getRegions());
        model.addAttribute("maritalStatus",validators.getMaritalStatus());
        model.addAttribute("carrers",validators.getCareers());
        return model;
    }
}