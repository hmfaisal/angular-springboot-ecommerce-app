package com.basketbird.backend.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.basketbird.backend.exception.UsernameInUseException;
import com.basketbird.backend.model.User;
import com.basketbird.backend.model.type.UserRole;
import com.basketbird.backend.service.EmailService;
import com.basketbird.backend.service.UserService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping( value = "/api", produces = MediaType.APPLICATION_JSON_VALUE )
public class SignupController {
	
	@Autowired
    private UserService userService;
	@Autowired
    private EmailService emailService;
    @Autowired
   	private PasswordEncoder passwordEncoder;
	
	@RequestMapping(value = "/register", method = POST)
	public ResponseEntity<?> register(User user,HttpServletRequest request, HttpServletResponse response) throws UsernameInUseException{
		
		if (this.userService.loadUserByUsername(user.getUsername()) != null) {
            throw new UsernameInUseException();
        }
		
		user.setConfirmationToken(UUID.randomUUID().toString());  
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setProviderId("Local");
		user.setProviderUserId(UUID.randomUUID().toString());
		user.grantRole(UserRole.USER);
		this.userService.saveUser(user);
		
/*
		String appUrl = request.getScheme() + "://" + request.getServerName()+":"+request.getServerPort();
		SimpleMailMessage registrationEmail = new SimpleMailMessage();
		registrationEmail.setTo(user.getEmail());
		registrationEmail.setSubject("Registration Confirmation");
		registrationEmail.setText("To confirm your e-mail address, please click the link below:\n"
					+ appUrl + "/api/confirm?token=" + user.getConfirmationToken());
		registrationEmail.setFrom("admin@basketbird.com");
			
		emailService.sendEmail(registrationEmail);
*/
					
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
    
	// Process confirmation link
	@RequestMapping(value="/confirm", method = GET)
	public void confirmregister(@RequestParam ("token") String token,HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
				
		//Map<String, String> result = new HashMap<>();
		User user = userService.loadUserByConfirmationToken(token);
                user.setAccountEnabled(true);
		this.userService.saveUser(user);
		
		//String appUrl = this.getBaseUrl(request);
		response.sendRedirect(this.getBaseUrl(request));		
	}

	String getBaseUrl(HttpServletRequest req) {
	    return req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort();
	}
	   
}
