package it.unicam.ing.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.unicam.ing.models.MyUser;
import it.unicam.ing.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
    private UserRepository userRepository;
    @Override
    public MyUser loadUserByUsername(String s) throws UsernameNotFoundException {
    	
        return userRepository.findByUsername(s);
    }
}
