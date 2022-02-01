package mx.edu.utez.sifu.student;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class StudentController {
    
    @Autowired
    private UserService userService;

    @PostMapping("")
    public String guardar(@Valid Student student, BindingResult result) {
        if (result.hasErrors()) {
            return "";
        }
        userService.save(student);

        return "redirect:" + "";
    }
}
