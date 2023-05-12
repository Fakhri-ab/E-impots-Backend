package tn.arabsoft.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import tn.arabsoft.spring.entities.DeclarationIRPP;
import tn.arabsoft.spring.services.DeclarationIRPPService;

import java.util.List;

@RestController
public class declarationIRPPController {

    @Autowired
    DeclarationIRPPService declarationIRPPService ;

    @GetMapping("/getallDeclarationIRPP")
    @ResponseBody
    public Page<DeclarationIRPP> getallDeclarationIRPP(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "5") int size, @RequestParam(required = false)String recherche) {
        PageRequest pr=PageRequest.of(page, size);
        return  declarationIRPPService.getalldeclarationsIRPP(pr,recherche) ;
    }

    @PostMapping("/ajouterDeclarationIRPP/{idUser}")
    public DeclarationIRPP ajouterDeclarationIRPP(@RequestBody DeclarationIRPP declarationIRPP , @PathVariable("idUser") int idUser){
        return   declarationIRPPService.addDeclarationIRPP(declarationIRPP,idUser);
    }

    @GetMapping("/retrieve-DeclarationIRPP/{DeclarationIRPPId}")
    public DeclarationIRPP retrieveDeclarationIRPP(@PathVariable("DeclarationIRPPId") int DeclarationIRPPId) {
        return declarationIRPPService.getDeclarationIRPPById(DeclarationIRPPId);
    }

    @GetMapping("/getallDeclarationIRPPbyUserid/{idUser}")
    public List<DeclarationIRPP>  getalldeclarationIRPPbyUserid(@PathVariable("idUser") int idUser) {
        return declarationIRPPService.getallDeclarationIRPPByUserId(idUser);
    }

    @DeleteMapping("/deleteDeclarationIRPP/{id}")
    @ResponseBody
    public void deleteDeclarationIRPP(@PathVariable("id") Integer DeclarationIRPPId){
        declarationIRPPService.deleteDeclarationIRPP(DeclarationIRPPId);
    }
}
