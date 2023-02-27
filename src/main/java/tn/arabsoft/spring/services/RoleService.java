package tn.arabsoft.spring.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import tn.arabsoft.spring.entities.Role;
import tn.arabsoft.spring.repositories.RoleRepository;


@Service
public class RoleService {
	@Autowired
	private RoleRepository rp;
	
	public Role createNewRole(Role role){
		return rp.save(role)	;
		 }
	
	public List<Role> getAllRole(){
			return (List<Role>) rp.findAll();
	}

	public void  deleteRole(int idRole){
		this.rp.deleteById(idRole);
	}

}
