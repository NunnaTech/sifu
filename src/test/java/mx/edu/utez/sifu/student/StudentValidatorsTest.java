package mx.edu.utez.sifu.student;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StudentValidatorsTest {
    @Test
    void testRegion() {

    }

    @Test
    void testBirthdate() {
        StudentValidators student = new StudentValidators();
        assertEquals("2001-06-27", student.birthdate("2001-06-27", 20));
        assertEquals("2001-06-27", student.birthdate("2001-06-27", 21));
    }

    @Test
    void testCareer() {

    }

    @Test
    void testCurp() {

    }

    @Test
    void testGender() {

    }

    @Test
    void testGetCareers() {

    }

    @Test
    void testGetGenders() {

    }

    @Test
    void testGetMaritalStatus() {

    }

    @Test
    void testGetRegions() {

    }

    @Test
    void testMaritalStatus() {

    }
}
