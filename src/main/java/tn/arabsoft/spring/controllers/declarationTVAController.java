package tn.arabsoft.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import tn.arabsoft.spring.entities.DeclarationIRPP;
import tn.arabsoft.spring.entities.DeclarationTVA;
import tn.arabsoft.spring.services.DeclarationTVAService;

import java.util.List;

@RestController
public class declarationTVAController {
    @Autowired
    DeclarationTVAService declarationTVAService ;

    @GetMapping("/getallDeclarationTVA")
    @ResponseBody
    public Page<DeclarationTVA> getallDeclarationTVA(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "5") int size, @RequestParam(required = false)String recherche) {
        PageRequest pr=PageRequest.of(page, size);
        return  declarationTVAService.getalldeclarationsTVA(pr,recherche) ;
    }

    @PostMapping("/ajouterDeclarationTVA/{idUser}")
    public DeclarationTVA ajouterDeclarationTVA(@RequestBody DeclarationTVA declarationTVA , @PathVariable("idUser") int idUser){
        return   declarationTVAService.addDeclarationTVA(declarationTVA,idUser);
    }

    @GetMapping("/retrieve-DeclarationTVA/{DeclarationTVAId}")
    public DeclarationTVA retrieveDeclarationTVA(@PathVariable("DeclarationTVAId") int DeclarationTVAId) {
        return declarationTVAService.getDeclarationTVAById(DeclarationTVAId);
    }

    @GetMapping("/getallDeclarationTVAbyUserid/{idUser}")
    public List<DeclarationTVA> getallDeclarationTVAbyUserid(@PathVariable("idUser") int idUser) {
        return declarationTVAService.getallDeclarationTVAByUserId(idUser);
    }

    @DeleteMapping("/deleteDeclarationTVA/{id}")
    @ResponseBody
    public void deleteDeclarationTVA(@PathVariable("id") Integer DeclarationTVAId){
        declarationTVAService.deleteDeclarationTVA(DeclarationTVAId);
    }
}
