package tn.arabsoft.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import tn.arabsoft.spring.entities.DeclarationIRPP;
import tn.arabsoft.spring.entities.Reclamation;
import tn.arabsoft.spring.entities.User;
import tn.arabsoft.spring.repositories.DeclarationIRPPRepo;
import tn.arabsoft.spring.repositories.UserRepositroy;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeclarationIRPPService {
    @Autowired
    DeclarationIRPPRepo declarationIRPPRepo ;

    @Autowired
    UserRepositroy userRepositroy ;

    public Page<DeclarationIRPP> getalldeclarationsIRPP(PageRequest pr, String recherche){
        if (recherche.equals(""))
            return (Page<DeclarationIRPP>) declarationIRPPRepo.findAll(pr);
        List<DeclarationIRPP> DeclarationIRPPs = declarationIRPPRepo.findAll().stream()
                .filter(declarationIRPP -> declarationIRPP.getFullName().contains(recherche))
                .collect(Collectors.toList());

        int start = (int) pr.getOffset();
        int end = (int) ((start + pr.getPageSize()) > DeclarationIRPPs.size() ? DeclarationIRPPs.size()
                : (start + pr.getPageSize()));

        Page<DeclarationIRPP> alluserpage = new PageImpl<>(DeclarationIRPPs.subList(start, end),pr,DeclarationIRPPs.size());
        return alluserpage;
    }

    public List<DeclarationIRPP>getallDeclarationIRPPByUserId(int id){
        return declarationIRPPRepo.getallDeclarationIRPPByUserId(id);
    }

    public DeclarationIRPP addDeclarationIRPP(DeclarationIRPP Di, int idUser){
      //  System.out.println(Di);
        User u = userRepositroy.findById(idUser).orElse(null);
         Di.setUser(u);
        return declarationIRPPRepo.save(Di) ;
    }

    public DeclarationIRPP getDeclarationIRPPById(int id){
        return declarationIRPPRepo.findById(id).orElse(null) ;
    }

    public void deleteDeclarationIRPP(int id){ declarationIRPPRepo.deleteById(id);}
}
