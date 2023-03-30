package tn.arabsoft.spring.entities;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String userName;
	private String userFName;
	private String userLName;
	private String email;
	private String Password;
	private boolean enabled;
	private String verificationcode;
	private String resetPasswordToken;

	@OneToMany(mappedBy = "user")
	private  List <Reclamation> Reclamations;

	@ManyToMany(fetch =FetchType.EAGER, cascade=CascadeType.MERGE)
	private Set<Role> role;

	@OneToOne
	private infoGenerale infogenerale ;




	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserFName() {
		return userFName;
	}


	public void setUserFName(String userFName) {
		this.userFName = userFName;
	}


	public String getUserLName() {
		return userLName;
	}


	public void setUserLName(String userLName) {
		this.userLName = userLName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return Password;
	}


	public void setPassword(String password) {
		Password = password;
	}


	public Set<Role> getRole() {
		return role;
	}


	public void setRole(Set<Role> role) {
		this.role = role;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public String getVerificationcode() {
		return verificationcode;
	}


	public void setVerificationcode(String verificationcode) {
		this.verificationcode = verificationcode;
	}


	public String getResetPasswordToken() {
		return resetPasswordToken;
	}


	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}


	

	
	
	
	

}
