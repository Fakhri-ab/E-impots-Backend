package tn.arabsoft.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import tn.arabsoft.spring.entities.DeclarationIRPP;
import tn.arabsoft.spring.entities.DeclarationTVA;
import tn.arabsoft.spring.entities.User;
import tn.arabsoft.spring.repositories.DeclarationTVARepo;
import tn.arabsoft.spring.repositories.UserRepositroy;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeclarationTVAService {

    @Autowired
    DeclarationTVARepo declarationTVARepo ;
    @Autowired
    UserRepositroy userRepositroy ;

    public List<DeclarationTVA> getallDeclarationTVA(){
        return declarationTVARepo.findAll();
    }

    public Page<DeclarationTVA> getalldeclarationsTVA(PageRequest pr, String recherche){
        if (recherche.equals(""))
            return (Page<DeclarationTVA>) declarationTVARepo.findAll(pr);
        List<DeclarationTVA> DeclarationTVAs = declarationTVARepo.findAll().stream()
                .filter(declarationTVA -> declarationTVA.getFullName().contains(recherche))
                .collect(Collectors.toList());

        int start = (int) pr.getOffset();
        int end = (int) ((start + pr.getPageSize()) > DeclarationTVAs.size() ? DeclarationTVAs.size()
                : (start + pr.getPageSize()));

        Page<DeclarationTVA> alluserpage = new PageImpl<>(DeclarationTVAs.subList(start, end),pr,DeclarationTVAs.size());
        return alluserpage;
    }

    public List<DeclarationTVA>getallDeclarationTVAByUserId(int id){
        return declarationTVARepo.getallDeclarationTVAByUserId(id);
    }

    public DeclarationTVA addDeclarationTVA(DeclarationTVA Dt, int idUser){
        //  System.out.println(Di);
        User u = userRepositroy.findById(idUser).orElse(null);
        Dt.setUser(u);
        Dt.setSituationFiscale("Non payee");
        return declarationTVARepo.save(Dt) ;
    }

    public DeclarationTVA getDeclarationTVAById(int id){
        return declarationTVARepo.findById(id).orElse(null) ;
    }

    public void deleteDeclarationTVA(int id){ declarationTVARepo.deleteById(id);}
}
