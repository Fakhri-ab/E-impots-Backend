package tn.arabsoft.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.arabsoft.spring.entities.Reclamation;
import tn.arabsoft.spring.entities.infoGenerale;

import java.util.List;

@Repository
public interface infoGeneraleRepo extends JpaRepository<infoGenerale, Integer> {

    @Query("SELECT i FROM infoGenerale i INNER JOIN i.user u WHERE u.id =:user_id")
    List<infoGenerale> getallinfoGeneraleByUserId(@Param("user_id") int userId);
}
