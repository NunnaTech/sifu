package mx.edu.utez.sifu.student;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.lang.reflect.Field;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<Student> getAll() {
        return (List<Student>) userRepository.findAll();
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
