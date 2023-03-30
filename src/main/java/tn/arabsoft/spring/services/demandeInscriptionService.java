package tn.arabsoft.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import tn.arabsoft.spring.entities.Reclamation;
import tn.arabsoft.spring.entities.StatusDemandeInscription;
import tn.arabsoft.spring.entities.demandeInscription;
import tn.arabsoft.spring.repositories.demandeInscriptionRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class demandeInscriptionService {

    @Autowired
    demandeInscriptionRepo demandeInscriptionRepo ;
    public Page<demandeInscription> getallDemandes(PageRequest pr, String recherche){
        if (recherche.equals(""))
            return (Page<demandeInscription>) demandeInscriptionRepo.findAll(pr);

        List<demandeInscription> demandes = demandeInscriptionRepo.findAll().stream()
                .filter(demandeInscription ->  demandeInscription.getUserFName().contains(recherche) || demandeInscription.getUserName().contains(recherche)
                || demandeInscription.getEmail().contains(recherche) )
                .collect(Collectors.toList());

        System.out.println("demandes " +demandes );

        int start = (int) pr.getOffset();
        int end = (int) ((start + pr.getPageSize()) > demandes.size() ? demandes.size()
                : (start + pr.getPageSize()));

        Page<demandeInscription> allDemandepage = new PageImpl<>(demandes.subList(start, end),pr,demandes.size());
        System.out.println("allDemandepage " +allDemandepage );
        return allDemandepage;
    }

    public demandeInscription ajouterDemande(demandeInscription demande){
        demandeInscription demandeinsc ;
        demande.setStatus(StatusDemandeInscription.Nouveau);
        demandeinsc = demandeInscriptionRepo.save(demande) ;
       return demandeinsc ;
    }

    public demandeInscription updateDemande(demandeInscription demande){
        return demandeInscriptionRepo.save(demande) ;
    }

    public void  deleteDemande(int id){
        demandeInscriptionRepo.deleteById(id);
    }


}
