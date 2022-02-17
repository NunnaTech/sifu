package mx.edu.utez.sifu.student;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StudentValidatorsTest {

    @Test
    void testBirthdate() {
        StudentValidators student = new StudentValidators();
        assertEquals("2001-06-27", student.birthdate("2001-06-27", 20));
        assertEquals("2001-06-27", student.birthdate("2001-06-27", 21));
    }
}
