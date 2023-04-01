package tn.arabsoft.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.arabsoft.spring.entities.Reclamation;
import tn.arabsoft.spring.entities.infoGenerale;
import tn.arabsoft.spring.services.infoGeneraleService;

import java.util.List;

@RestController
public class infoGeneraleController {

    @Autowired
    infoGeneraleService infogeneraleservice ;

    @PostMapping("/ajouterInfo/{idUser}")
    public infoGenerale ajouterInfoGenerale(@RequestBody infoGenerale infogenerale , @PathVariable("idUser") int idUser){
        return   infogeneraleservice.addInfoGenerele(infogenerale,idUser);
    }

    @GetMapping("/retrieve-info/{infoId}")
    public infoGenerale retrieveinfoById(@PathVariable("infoId") int infoId) {
        return infogeneraleservice.getinfoById(infoId);
    }

    @GetMapping("/getallinfoGeneraleByUserId/{idUser}")
    public List<infoGenerale> getallinfoGeneraleByUserId(@PathVariable("idUser") int idUser) {
        return infogeneraleservice.getallinfoGeneraleByUserId(idUser);
    }

    @PutMapping("/modify-info/{id}")
    @ResponseBody
    public infoGenerale UpdateCategorie(@RequestBody infoGenerale i, @PathVariable("id") Integer infoId) {
        return infogeneraleservice.Updateinfo(i);
    }

    @PutMapping("modifyinfogenerale/{id}")
    public ResponseEntity<infoGenerale> updateInfoGenerale(@PathVariable int id, @RequestBody infoGenerale info) {
        infoGenerale updatedInfo = infogeneraleservice.updateInfoGenerale( info);
        return ResponseEntity.ok(updatedInfo);
    }
}
