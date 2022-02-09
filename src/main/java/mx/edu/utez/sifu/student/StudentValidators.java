package mx.edu.utez.sifu.student;

import org.springframework.stereotype.Service;

@Service
public class StudentValidators {
    private String[] genders = { "Masculino", "Femenino" };
    private String[] regions = { "México", "Estados Unidos", "Canadá", "Guatemala" };
    private String[] maritalStatus = { "Soltero(a)", "Casado(a)", "Viudo(a)", "Divorciado(a)", "Unión Libre" };
    private String[] careers = { "Ingeniería en Desarrollo y Gestión de Software", "Ingeniería en Diseño Textil y Modas",
    "Licenciatura en Diseño Digital y Producción Audiovisual", 
    "Ingeniería en Redes Inteligentes y Ciberseguridad"};

    public String[] getGenders() {
        return this.genders;
    }

    public String[] getRegions() {
        return this.regions;
    }

    public String[] getMaritalStatus() {
        return this.maritalStatus;
    }
    
    public String[] getCareers() {
        return this.careers;
    }

    // Validators methods
    
    public String gender(String genderResponse){
        for (String gender : genders) {
            if (genderResponse.equals(gender)) return gender;
        }
        return "";
    }

    public String Region(String countryResponse){
        for (String country : regions) {
            if (countryResponse.equals(country)) return country;
        }
        return "";
    }


    public String maritalStatus(String statusResponse){
        for (String status : maritalStatus) {
            if (statusResponse.equals(status)) return status;
        }
        return "";
    } 

    public String career(String carrerResponse){
        for (String career : careers) {
            if (carrerResponse.equals(career)) return career;
        }
        return "";
    }
}
