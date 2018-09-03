package pw.io.booker.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import pw.io.booker.model.Customer;
import pw.io.booker.model.Token;
import pw.io.booker.repo.TokenRepository;

@Service
public class TokenCreator {
	
	private TokenRepository tokenRepository;

	public TokenCreator(TokenRepository tokenRepository) {
		this.tokenRepository = tokenRepository;
	}
	
  private static String source = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789@ ";
  private static String target = "Q5A8ZWS0XEDC6RFVT9GBY4HNU3J2MI1KO7LPX#";

  public String encode(Customer customer) {
    String toObfuscate = new String(customer.getUsername() + customer.getPassword()
        + LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
    toObfuscate = getScrambledWord(toObfuscate.toUpperCase());

    char[] result = new char[toObfuscate.length()];
    for (int i = 0; i < toObfuscate.length(); i++) {
      char c = toObfuscate.charAt(i);
      int index = source.indexOf(c);
      result[i] = target.charAt(index);
    }
    
    return new String(result);
  }

  private String getScrambledWord(String str) {
    char[] character = str.toCharArray();
    String question1 = new String();

    ArrayList<Character> chars = new ArrayList<Character>();
    for (int i = 0; i < character.length; i++) {
      chars.add(character[i]);
    }

    while (chars.size() > 0) {
      int index = (int) (Math.random() * chars.size());
      question1 += chars.get(index);
      chars.remove(index);
    }

    return question1;
  }

public void delete(String token) {
	
	tokenRepository.deleteByTokenString(token);
	
}
  

}
