package game.mozzi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MozziApplication {

	public static void main(String[] args) {
		SpringApplication.run(MozziApplication.class, args);
	}

}
