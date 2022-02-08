package mx.edu.utez.sifu.student;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.lang.reflect.Field;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
public class StudentService {

    @Autowired
    private UserRepository userRepository;

    public List<Student> getAll() {
        return (List<Student>) userRepository.findAll();
    }

    // Pagination
    public Page<Student> findPaginated(Pageable pageable) {
        List<Student> students = (List<Student>) userRepository.findAll();
        
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Student> list;

        if (students.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, students.size());
            list = students.subList(startItem, toIndex);
        }

        Page<Student> studentPage
          = new PageImpl<Student>(list, PageRequest.of(currentPage, pageSize), students.size());

        return studentPage;
    }


    public Optional<Student> getById(int id) {
        return userRepository.findById(id);
    }

    public Student save(Student entity) {
        return userRepository.save(entity);
    }

    public Optional<Student> update(Student entity) {
        Optional<Student> updatedEntity = Optional.empty();
        updatedEntity = userRepository.findById(entity.getId());
        if (!updatedEntity.isEmpty())
            userRepository.save(entity);
        return updatedEntity;
    }

    public Optional<Student> partialUpdate(Integer id, Map<Object, Object> fields) {
        try {
            Student entity = getById(id).get();
            if (entity == null) {
                return Optional.empty();
            }
            Optional<Student> updatedEntity = Optional.empty();
            // Map key is field name, v is value
            fields.forEach((updatedField, value) -> {
                // use reflection to get fields updatedField on manager and set it to value updatedField
                Field field = ReflectionUtils.findField(Student.class, (String) updatedField);
                field.setAccessible(true);
                ReflectionUtils.setField(field, entity, value);
            });
            userRepository.save(entity);
            updatedEntity = Optional.of(entity);
            return updatedEntity;
        } catch (Exception exception) {
            System.err.println(exception);
            return Optional.empty();
        }
    }

    public Optional<Student> delete(int id) {
        Optional<Student> entity = getById(id);
        if(entity.isPresent()){
            userRepository.delete(entity.get());
        }
        return entity;
    }


}
