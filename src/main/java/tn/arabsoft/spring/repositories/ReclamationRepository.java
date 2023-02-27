package tn.arabsoft.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.arabsoft.spring.entities.Reclamation;
import tn.arabsoft.spring.entities.StatusReclamation;
import tn.arabsoft.spring.entities.typeReclamation;

import java.util.Date;
import java.util.List;

@Repository
public interface ReclamationRepository extends JpaRepository<Reclamation, Integer> {

    @Query(value="SELECT COUNT(*) FROM reclamation",nativeQuery=true)
    Integer getTotalReclamation();
    @Query(value="SELECT COUNT(*) FROM Reclamation r WHERE r.Status LIKE '%New' ")
    Integer getNbNewReclamation();
    @Query(value="SELECT COUNT(*) FROM Reclamation r WHERE r.Status LIKE '%Pending' ")
    Integer getNbPendingReclamation();
    @Query(value="SELECT COUNT(*) FROM Reclamation r WHERE r.Status LIKE '%Treated' ")
    Integer getNbTreatedReclamation();

    @Query("SELECT r FROM Reclamation r WHERE r.typeReclamation =:type AND r.DateOfReclam =:d ")
    List<Reclamation> FiltrerReclamationsByDateAndType(@Param("type") typeReclamation type, @Param("d") Date d);

    @Query("SELECT r FROM Reclamation r WHERE r.Status =:status AND  r.DateOfReclam=:d ")
    List<Reclamation> FiltrerReclamationsByDateAndStatus(@Param("status") StatusReclamation status, @Param("d") Date d);

    @Query("SELECT r FROM Reclamation r WHERE r.Status =:status ORDER BY r.DateOfReclam ASC")
    List<Reclamation> getListReclamationsByStatusSorted(@Param("status") StatusReclamation status);

    @Query("SELECT r FROM Reclamation r WHERE r.typeReclamation =:type ORDER BY r.DateOfReclam ASC")
    List<Reclamation> getListReclamationsByTypeSorted(@Param("type") typeReclamation type);




    @Query("SELECT r FROM Reclamation r WHERE r.typeReclamation =:type AND r.Status IN :status")
    List<Reclamation> getallReclamationsByTypeAndStatus(@Param("type") typeReclamation type, @Param("status") StatusReclamation status);

    @Query("SELECT r FROM Reclamation r WHERE r.DateOfReclam=:date")
    List<Reclamation> searchReclamationByDate(@Param("date") Date date);
}
