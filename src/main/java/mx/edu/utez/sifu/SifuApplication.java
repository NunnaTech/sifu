package mx.edu.utez.sifu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class SifuApplication {

	public static void main(String[] args) {
		SpringApplication.run(SifuApplication.class, args);
	}

}
