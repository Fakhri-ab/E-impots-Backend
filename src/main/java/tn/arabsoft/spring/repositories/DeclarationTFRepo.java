package tn.arabsoft.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.arabsoft.spring.entities.DeclarationTF;

import java.util.List;

@Repository
public interface DeclarationTFRepo extends JpaRepository<DeclarationTF,Integer> {

    @Query("SELECT i FROM DeclarationTF i INNER JOIN i.user u WHERE u.id =:user_id")
    List<DeclarationTF> getallDeclarationTFByUserId(@Param("user_id") int userId);
}
