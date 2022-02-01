package mx.edu.utez.sifu.user;

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

    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

    public Optional<User> getById(int id) {
        return userRepository.findById(id);
    }

    public User save(User entity) {
        return userRepository.save(entity);
    }

    public Optional<User> update(User entity) {
        Optional<User> updatedEntity = Optional.empty();
        updatedEntity = userRepository.findById(entity.getId());
        if (!updatedEntity.isEmpty())
            userRepository.save(entity);
        return updatedEntity;
    }

    public Optional<User> partialUpdate(Integer id, Map<Object, Object> fields) {
        try {
            User entity = getById(id).get();
            if (entity == null) {
                return Optional.empty();
            }
            Optional<User> updatedEntity = Optional.empty();
            // Map key is field name, v is value
            fields.forEach((updatedField, value) -> {
                // use reflection to get fields updatedField on manager and set it to value updatedField
                Field field = ReflectionUtils.findField(User.class, (String) updatedField);
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

    public Optional<User> delete(int id) {
        Optional<User> entity = getById(id);
        if(entity.isPresent()){
            userRepository.delete(entity.get());
        }
        return entity;
    }
}
