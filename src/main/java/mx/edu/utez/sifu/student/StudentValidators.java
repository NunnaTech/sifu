package mx.edu.utez.sifu.student;

import java.time.LocalDate; 
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import mx.edu.utez.sifu.curp.Result;
import mx.edu.utez.sifu.curp.Solicitante;

@Service
@Slf4j
public class StudentValidators {

    private String url = "https://api.valida-curp.com.mx/curp/obtener_datos/?token=pruebas&curp=";

    @Autowired
    private UserRepository repository;

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

    public String birthdate(String birthdateRequest, Integer age){
        try {
            LocalDate now = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
            LocalDate birthdate = LocalDate.parse(birthdateRequest, formatter);
            // check
            int years = (int) ChronoUnit.YEARS.between(now,birthdate);
    
            if(Math.abs(years) == age){
                return birthdateRequest;
            }
            return "";
             
        } catch (Exception e) {
            return "";
            
        }
    }

    public String curp(String curpRequest){
        return "";
        
    }

}
