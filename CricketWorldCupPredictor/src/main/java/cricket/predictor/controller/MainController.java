package cricket.predictor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	
	@GetMapping("/error")
	public String showErrorForm(Model model) {
		return "error";
	}
	
	@GetMapping("/success-form")
	public String showSuccessForm(Model model) {
		return "success-form";
	}
	
	@GetMapping("/failure-form")
	public String showFailureForm(Model model) {
		return "failure-form";
	}

}
