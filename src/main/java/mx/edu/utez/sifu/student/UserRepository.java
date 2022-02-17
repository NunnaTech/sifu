package mx.edu.utez.sifu.student;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<Student, Integer> {
    Optional<Student> findByCurp(String curp); 
}
