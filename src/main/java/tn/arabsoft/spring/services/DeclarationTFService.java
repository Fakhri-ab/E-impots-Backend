package tn.arabsoft.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import tn.arabsoft.spring.entities.DeclarationTF;
import tn.arabsoft.spring.entities.DeclarationTVA;
import tn.arabsoft.spring.entities.User;
import tn.arabsoft.spring.repositories.DeclarationTFRepo;
import tn.arabsoft.spring.repositories.UserRepositroy;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeclarationTFService {
    @Autowired
    DeclarationTFRepo declarationTFRepo ;
    @Autowired
    UserRepositroy userRepositroy ;

    public Page<DeclarationTF> getalldeclarationsTF(PageRequest pr, String recherche){
        if (recherche.equals(""))
            return (Page<DeclarationTF>) declarationTFRepo.findAll(pr);
        List<DeclarationTF> DeclarationTFs = declarationTFRepo.findAll().stream()
                .filter(declarationTF -> declarationTF.getFullNameProprietaire().contains(recherche))
                .collect(Collectors.toList());

        int start = (int) pr.getOffset();
        int end = (int) ((start + pr.getPageSize()) > DeclarationTFs.size() ? DeclarationTFs.size()
                : (start + pr.getPageSize()));

        Page<DeclarationTF> alluserpage = new PageImpl<>(DeclarationTFs.subList(start, end),pr,DeclarationTFs.size());
        return alluserpage;
    }

    public List<DeclarationTF>getallDeclarationTFByUserId(int id){
        return declarationTFRepo.getallDeclarationTFByUserId(id);
    }

    public DeclarationTF addDeclarationTVA(DeclarationTF Dt, int idUser){
        //  System.out.println(Di);
        User u = userRepositroy.findById(idUser).orElse(null);
        Dt.setUser(u);
        Dt.setSituationFiscale("Non payee");
        return declarationTFRepo.save(Dt) ;
    }

    public DeclarationTF getDeclarationTFById(int id){
        return declarationTFRepo.findById(id).orElse(null) ;
    }

    public void deleteDeclarationTF(int id){ declarationTFRepo.deleteById(id);}
}
