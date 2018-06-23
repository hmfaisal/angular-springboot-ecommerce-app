package com.basketbird.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping( value = "/api", produces = MediaType.APPLICATION_JSON_VALUE )
public class FacebookController {

    @Autowired
    Facebook facebook;

    @RequestMapping(value = "/facebook/details", method = GET)
    public User getSocialDetails() {
        return facebook.userOperations().getUserProfile();
    }
    
    @RequestMapping(value = "/fbSignin", method = GET)
    public String postFbLogin(HttpServletResponse response) throws IOException {
    	response.sendRedirect("http://localhost:8080/auth/facebook");
    	//return "redirect:http://localhost:8080/auth/facebook";
		return null;
    }
}
