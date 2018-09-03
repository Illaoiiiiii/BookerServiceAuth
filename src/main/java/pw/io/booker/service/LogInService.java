package pw.io.booker.service;

import org.springframework.stereotype.Service;

import pw.io.booker.model.Customer;
import pw.io.booker.model.Token;
import pw.io.booker.repo.AuthenticationRepository;
import pw.io.booker.util.TokenCreator;

@Service
public class LogInService {

	private AuthenticationRepository authenticationRepository;
	private TokenCreator tokenCreator;

	public LogInService(AuthenticationRepository authenticationRepository,TokenCreator tokenCreator) {
		this.authenticationRepository = authenticationRepository;
		this.tokenCreator = tokenCreator;
	}
	
	public String logIn(Customer customer) {
		return tokenCreator.encode(customer);
	}
	public void logOut(Token token) {
		authenticationRepository.delete(token);
	}
}