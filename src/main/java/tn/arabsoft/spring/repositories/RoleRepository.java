package tn.arabsoft.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import tn.arabsoft.spring.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
	Role findByRoleName(String roleName);

}
