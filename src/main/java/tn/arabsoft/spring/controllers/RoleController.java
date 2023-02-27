package tn.arabsoft.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tn.arabsoft.spring.entities.Role;
import tn.arabsoft.spring.services.RoleService;


@CrossOrigin
@RestController
public class RoleController {
	
	@Autowired
	private RoleService rs;
	
	
	@PostMapping({"/createNewRole"})
	@ResponseBody
	public Role createNewRole(@RequestBody Role role){
	return  rs.createNewRole(role);	
	}

	
	@GetMapping({"/getAllRole"})
	@ResponseBody
	public List<Role> getallRole(){
		return  (List<Role>) rs.getAllRole();
	}
	@DeleteMapping("/deleteRole/{idRole}")
	@ResponseBody
	public void removefacture(@PathVariable("idRole") int idRole) {
	rs.deleteRole(idRole);
	}
}
