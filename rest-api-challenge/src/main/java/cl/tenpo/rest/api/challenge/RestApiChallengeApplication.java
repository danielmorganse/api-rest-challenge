package cl.tenpo.rest.api.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RestApiChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiChallengeApplication.class, args);
	}

}
