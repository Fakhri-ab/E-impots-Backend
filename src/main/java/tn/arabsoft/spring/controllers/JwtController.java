package tn.arabsoft.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tn.arabsoft.spring.entities.JwtRequest;
import tn.arabsoft.spring.entities.JwtResponse;
import tn.arabsoft.spring.services.JwtService;


@RestController
@CrossOrigin
public class JwtController {
	
	@Autowired
	private JwtService jwts;
	
	@PostMapping({"/auth"})
	public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		return jwts.createJwtToken(jwtRequest);
	}

}
