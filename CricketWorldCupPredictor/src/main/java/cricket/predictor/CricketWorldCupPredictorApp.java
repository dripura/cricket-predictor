package cricket.predictor;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "cricket.predictor")

public class CricketWorldCupPredictorApp {

	public static final List<String> teams = Arrays.asList("Aghanistan", "Australia", "Bangladesh", "England", "India",
			"Netherlands", "New Zealand", "Pakistan", "South Africa", "Sri Lanka");

	public static void main(String[] args) {
		SpringApplication.run(CricketWorldCupPredictorApp.class, args);
	}

}
