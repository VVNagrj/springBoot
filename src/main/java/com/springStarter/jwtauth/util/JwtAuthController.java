package com.springStarter.jwtauth.util;

import com.springStarter.mongoDB.bookstore.User.User;
import com.springStarter.mongoDB.bookstore.User.UserRepository;
import com.springStarter.jwtauth.dto.JwtAuthenticationRequest;
import com.springStarter.jwtauth.dto.JwtAuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
class JwtAuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws Exception {

		String username = authenticationRequest.getUsername();
		String password = authenticationRequest.getPassword();

		// Check the database for the provided username and password
		User userEntity = userRepository.findByUsernameAndPassword(username,password);

		if (userEntity == null) {
			throw new BadCredentialsException("Incorrect username or password");
		} else {

			try {
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
				);
			} catch (BadCredentialsException e) {
				throw new Exception("Incorrect username or password", e);
			}

			final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
			final String jwt = jwtTokenUtil.generateToken(userDetails);

			return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
		}
	}

}