package it.unicam.ing.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.unicam.ing.DTO.LoginRequestDTO;
import it.unicam.ing.DTO.LoginResponseDTO;
import it.unicam.ing.helper.JwtUtil;
import it.unicam.ing.models.MyUser;
import it.unicam.ing.service.AuthenticationService;
import it.unicam.ing.service.MyUserDetailsService;




@RestController
public class LoginController {

	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public LoginResponseDTO createAuthenticationToken(@RequestBody LoginRequestDTO authenticationRequest) throws Exception {	
		

		boolean logged = false;
		try {
			logged = authenticationService.tryAuthentication(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		if(logged) {
		final MyUser userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails);	
		return new LoginResponseDTO(jwt,userDetails.getRole().toString());
		}		
		throw new Exception("Incorrect username or password");
	}

}
