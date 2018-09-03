package pw.io.booker.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import pw.io.booker.model.Customer;
import pw.io.booker.model.Token;
import pw.io.booker.repo.CustomerRepository;
import pw.io.booker.repo.TokenRepository;
import pw.io.booker.util.TokenCreator;

@Service
public class LogInService {

	private CustomerRepository customerRepository;
	private TokenRepository tokenRepository;
	private TokenCreator tokenCreator;

	public LogInService(CustomerRepository customerRepository,TokenRepository tokenRepository,TokenCreator tokenCreator) {
		super();
		this.customerRepository = customerRepository;
		this.tokenRepository = tokenRepository;
		this.tokenCreator = tokenCreator;
	}
	
	public String logIn(Customer customer) {
	    
		customerRepository.save(customer);
		
		String result;
		
		result = tokenCreator.encode(customer);
		
		Token token = new Token();
	    token.setCustomer(customer);
	    token.setTokenString(result);
	    
	    tokenRepository.save(token);
	    
		return result;
	}
	public void logOut(@RequestHeader String token) {
		tokenCreator.delete(token);
	}
}