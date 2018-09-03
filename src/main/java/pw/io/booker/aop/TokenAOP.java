package pw.io.booker.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

import pw.io.booker.repo.TokenRepository;

@Aspect
@Service
public class TokenAOP {

	private TokenRepository tokenRepository;
	
	public TokenAOP(TokenRepository tokenRepository) {
		this.tokenRepository = tokenRepository;
	}

	@Around("execution(* pw.io.booker.controller..*(..)) && args(token,..)")
	public Object checkAuthentication(ProceedingJoinPoint joinPoint, String token) {

		if (token == null) {
			throw new AuthenticationException();
		}

		if (tokenRepository.findByTokenString(token) == null) {
			throw new AuthenticationException();
		}
		
		Object returnS = null;

		try {
			returnS= joinPoint.proceed();
		} catch (AuthenticationException ex) {
			ex.getMessage();
		} catch (Throwable e) {
			e.getMessage();
		}

		return returnS;
	}
}
