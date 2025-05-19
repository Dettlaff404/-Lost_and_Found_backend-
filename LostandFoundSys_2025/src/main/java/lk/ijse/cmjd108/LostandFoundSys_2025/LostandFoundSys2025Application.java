package lk.ijse.cmjd108.LostandFoundSys_2025;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableJpaRepositories
@EnableWebMvc
public class LostandFoundSys2025Application {

	private static final Logger LOGGER = LoggerFactory.getLogger(LostandFoundSys2025Application.class);

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		LOGGER.info("Application started");
		SpringApplication.run(LostandFoundSys2025Application.class, args);
	}
	
}
