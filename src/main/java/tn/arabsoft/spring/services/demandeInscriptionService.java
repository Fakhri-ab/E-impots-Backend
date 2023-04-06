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

    @Autowired
    private EmailSenderService servicemail;

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
        servicemail.sendSimpleEmail(demandeinsc.getEmail(),"Bonjour,\n" + demandeinsc.getUserLName() + " "+ demandeinsc.getUserFName() +
                "\n" +
                "Nous vous remercions pour votre demande d'inscription à notre service. Nous avons bien reçu votre demande et nous mettons tout en œuvre pour la traiter dans les meilleurs délais. Notre équipe est mobilisée pour répondre à votre demande et vous accompagner tout au long du processus d'inscription.\n" +
                "\n" +
                "Nous restons à votre disposition pour toute information complémentaire et nous vous tiendrons informé(e) de l'avancement de votre demande.\n" +
                "\n" +
                "Cordialement","E-impots Demande d'inscription");
       return demandeinsc ;
    }

    public demandeInscription updateDemande(demandeInscription demande){
        return demandeInscriptionRepo.save(demande) ;
    }

    public void  deleteDemande(int id){
        demandeInscriptionRepo.deleteById(id);
    }


}
