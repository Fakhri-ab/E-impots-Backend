package tn.arabsoft.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.arabsoft.spring.entities.DeclarationIRPP;
import tn.arabsoft.spring.entities.DeclarationTVA;

import java.util.List;

@Repository
public interface DeclarationTVARepo extends JpaRepository<DeclarationTVA,Integer> {

    @Query("SELECT i FROM DeclarationTVA i INNER JOIN i.user u WHERE u.id =:user_id")
    List<DeclarationTVA> getallDeclarationTVAByUserId(@Param("user_id") int userId);
}
