package tn.arabsoft.spring.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.arabsoft.spring.entities.demandeInscription;


@Repository
public interface demandeInscriptionRepo extends JpaRepository<demandeInscription, Integer> {

}
