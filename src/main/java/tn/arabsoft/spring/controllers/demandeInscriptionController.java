package tn.arabsoft.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import tn.arabsoft.spring.entities.demandeInscription;
import tn.arabsoft.spring.services.demandeInscriptionService;

@RestController
public class demandeInscriptionController {

    @Autowired
    demandeInscriptionService demandeInscriptionService ;
    @GetMapping("/getallDemandes")
    @ResponseBody
    public Page<demandeInscription> getallDemandes(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "5") int size,
                                                   @RequestParam(required = false)String recherche){
        PageRequest pr=PageRequest.of(page, size);
      return   demandeInscriptionService.getallDemandes(pr,recherche) ;
    }

    @PostMapping("/ajouterDemande")
    public demandeInscription ajouterDemande(@RequestBody demandeInscription demandeInscription){
      return   demandeInscriptionService.ajouterDemande(demandeInscription);
    }

    @PutMapping("/modifyDemande/{id}")
    @ResponseBody
    public demandeInscription updateDemande(@RequestBody demandeInscription demande ,@PathVariable("id") Integer demandeId){
        return demandeInscriptionService.updateDemande(demande) ;
    }

    @DeleteMapping("/deleteDemande/{id}")
    @ResponseBody
    public void deleteDemande(@PathVariable("id") Integer demandeId){
        demandeInscriptionService.deleteDemande(demandeId);
    }
}
