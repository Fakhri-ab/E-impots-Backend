package tn.arabsoft.spring.entities;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tn.arabsoft.spring.entities.User;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
	
	private User user;
	private String jwtToken;
	

}
