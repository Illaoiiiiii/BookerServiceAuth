package pw.io.booker.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

import pw.io.booker.repo.TokenRepository;

@Aspect
@Service
public class TokenAOP {

	private TokenRepository tokenRepository;
	
	private static Logger logger = Logger.getLogger(TokenAOP.class);
	
	public TokenAOP(TokenRepository tokenRepository) {
		this.tokenRepository = tokenRepository;
	}

	@Around("execution(* pw.io.booker.controller..*(..)) && args(token,..)")
	public Object checkAuthentication(ProceedingJoinPoint joinPoint, String token) {

		logger.info("CheckAuthentication Entry Point");
		
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
			logger.error(ex.message());
		} catch (Throwable e) {
			logger.error(e.getMessage());
		}

		logger.info("CheckAuthentication Exit Point");
		return returnS;
	}
}
