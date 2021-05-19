package it.unicam.ing.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.unicam.ing.models.MyUser;
import it.unicam.ing.repository.UserRepository;

@Service
public class AuthenticationService {

	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder cryptoEncoder;
	
	public boolean tryAuthentication(String username,String password) {
		MyUser user = userRepository.findByUsername(username);
		if(user==null) return false;
		return (cryptoEncoder.matches(password, user.getPassword()));
				
	}
}
