package tn.arabsoft.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.arabsoft.spring.entities.DeclarationIRPP;


import java.util.List;

@Repository
public interface DeclarationIRPPRepo extends JpaRepository<DeclarationIRPP,Integer> {

    @Query("SELECT i FROM DeclarationIRPP i INNER JOIN i.user u WHERE u.id =:user_id")
    List<DeclarationIRPP> getallDeclarationIRPPByUserId(@Param("user_id") int userId);
}
