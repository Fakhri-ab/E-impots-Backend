package tn.arabsoft.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import tn.arabsoft.spring.entities.DeclarationTF;
import tn.arabsoft.spring.entities.DeclarationTVA;
import tn.arabsoft.spring.services.DeclarationTFService;

import java.util.List;

@RestController
public class declarationTFController {
    @Autowired
    DeclarationTFService declarationTFService ;

    @GetMapping("/getallDeclarationTF")
    @ResponseBody
    public Page<DeclarationTF> getallDeclarationTF(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "5") int size, @RequestParam(required = false)String recherche) {
        PageRequest pr=PageRequest.of(page, size);
        return  declarationTFService.getalldeclarationsTF(pr,recherche) ;
    }

    @PostMapping("/ajouterDeclarationTF/{idUser}")
    public DeclarationTF ajouterDeclarationTF(@RequestBody DeclarationTF declarationTF , @PathVariable("idUser") int idUser){
        return   declarationTFService.addDeclarationTVA(declarationTF,idUser);
    }

    @GetMapping("/retrieve-DeclarationTF/{DeclarationTFId}")
    public DeclarationTF retrieveDeclarationTVA(@PathVariable("DeclarationTFId") int DeclarationTFId) {
        return declarationTFService.getDeclarationTFById(DeclarationTFId);
    }

    @GetMapping("/getallDeclarationTFbyUserid/{idUser}")
    public List<DeclarationTF> getallDeclarationTFbyUserid(@PathVariable("idUser") int idUser) {
        return declarationTFService.getallDeclarationTFByUserId(idUser);
    }

    @DeleteMapping("/deleteDeclarationTF/{id}")
    @ResponseBody
    public void deleteDeclarationTVA(@PathVariable("id") Integer DeclarationTFId){
        declarationTFService.deleteDeclarationTF(DeclarationTFId);
    }
}
