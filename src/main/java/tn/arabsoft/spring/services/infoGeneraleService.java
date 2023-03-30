package tn.arabsoft.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.arabsoft.spring.entities.Reclamation;
import tn.arabsoft.spring.entities.User;
import tn.arabsoft.spring.entities.infoGenerale;
import tn.arabsoft.spring.repositories.UserRepositroy;
import tn.arabsoft.spring.repositories.infoGeneraleRepo;

import java.util.List;

@Service
public class infoGeneraleService {

    @Autowired
    private infoGeneraleRepo infogeneralerepo ;

    @Autowired
    UserRepositroy userRepositroy ;

    public infoGenerale addInfoGenerele(infoGenerale ig , int idUser ){
        User u = userRepositroy.findById(idUser).orElse(null);
        ig.setUser(u);
            return infogeneralerepo.save(ig) ;
    }

    public infoGenerale getinfoById(Integer id){
      return  infogeneralerepo.findById(id).orElse(null) ;
    }

    public List<infoGenerale> getallinfoGeneraleByUserId(int UserId) {
        List <infoGenerale> l =  infogeneralerepo.getallinfoGeneraleByUserId(UserId);
        return  l ;
    }


}
