package pw.io.booker.controller;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pw.io.booker.model.Customer;
import pw.io.booker.repo.CustomerRepository;
import pw.io.booker.service.LogInService;

@RestController()
@Transactional
@RequestMapping("/login")
public class LogInController {

	private LogInService logInService;
	private CustomerRepository customerRepository;
	
	
	public LogInController(LogInService logInService, CustomerRepository customerRepository) {
		super();
		this.logInService = logInService;
		this.customerRepository = customerRepository;
	}

	@PostMapping
	public String logInAuthentication(@RequestBody Customer customer) {
		return logInService.logIn(customer);
	}
	@DeleteMapping
	public void logOutAuthentication(@RequestHeader String token) {
		logInService.logOut(token);
	}
}
