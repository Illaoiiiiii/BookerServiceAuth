package pw.io.booker.controller;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pw.io.booker.model.Customer;
import pw.io.booker.model.Token;
import pw.io.booker.service.LogInService;

@RestController()
@Transactional
@RequestMapping("/login")
public class LogInController {

	private LogInService logInService;
	private Token token;
	
	public LogInController(LogInService logInService) {
		super();
		this.logInService = logInService;
	}

	@PostMapping
	public String logInAuthentication(@RequestBody Customer customer) {
		token.setTokenString(logInService.logIn(customer));
		return token.getTokenString();
	}
	@DeleteMapping
	public void logOutAuthentication(Token token) {
		logInService.logOut(token);
	}
}
