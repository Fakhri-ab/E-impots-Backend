package tn.arabsoft.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import tn.arabsoft.spring.entities.Reclamation;
import tn.arabsoft.spring.entities.StatusReclamation;
import tn.arabsoft.spring.entities.User;
import tn.arabsoft.spring.services.ReclamationService;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@CrossOrigin
@RestController
public class ReclamationControlleur {

    @Autowired
    ReclamationService reclamationService ;


    @GetMapping("/retrieve-all-Reclamations")
    public List<Reclamation> getReclamation() {
        List<Reclamation> listReclamations = reclamationService.retrieveAllReclamations()	;
        return listReclamations;
    }

    @GetMapping("/getalltReclamation")
    @ResponseBody
    public Page<Reclamation> getAllUser(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "5") int size, @RequestParam(required = false)String recherche) {
        PageRequest pr=PageRequest.of(page, size);
      return  reclamationService.getAllReclamations(pr,recherche) ;
    }

    @GetMapping("/retrieve-Reclamation/{ReclamationId}")
    public Reclamation retrieveReclamation(@PathVariable("ReclamationId") int ReclamationId) {
        return reclamationService.retrieveReclamationById(ReclamationId);
    }
    @PostMapping("/add-Reclamation/{idUser}")
    public Reclamation addReclamation(@RequestBody Reclamation r , @PathVariable("idUser") int idUser ) throws MessagingException, IOException {
        Reclamation Reclamation = reclamationService.addReclamation(r,idUser);
        return Reclamation;

    }


    @RequestMapping(value = "/genratepdf", method = RequestMethod.GET, produces = {"application/pdf"})
    @ResponseBody
    public void generatePDF(HttpServletResponse response ) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

     //   reclamationService.export(response);
    }
    @PutMapping("/modify-Reclamation/{idUser}/{idr}")
    public Reclamation modifyReclamation(@RequestBody Reclamation Reclamation , @PathVariable("idUser") int idUser, @PathVariable("idr") int idr ){
        return reclamationService.updateReclamation(Reclamation,idUser,idr);
    }

    @DeleteMapping("/remove-Reclamation/{id}")
    @ResponseBody
    public void removeReclamation(@PathVariable("id") int id) {
        reclamationService.deleteReclamationById(id);
    }

    @GetMapping("/getNbTotalReclamation")
    public int getNombreReclamation(){
        return reclamationService.getTotalReclamation();
    }

    @GetMapping("/getNbNewReclamation")
    public int getNbNewReclamation(){
        return reclamationService.getNbNewReclamation();
    }

    @GetMapping("/getNbPendingReclamation")
    public int getNbPendingReclamation(){
        return reclamationService.getNbPendingReclamation();
    }
    @GetMapping("/getNbTreatedReclamation")
    public int getNbTreatedReclamation(){
        return reclamationService.getNbTreatedReclamation();
    }

    @GetMapping("/getAllReclamationsByUserId/{idUser}")
    public List<Reclamation> getallReclamationsbyUserId(@PathVariable("idUser") int idUser) {
        return reclamationService.getallReclamationsByUserId(idUser);
    }

    @GetMapping("/getallReclamationsByStatusSorted/{status}")
    public List<Reclamation> getallReclamationsByStatusSorted(@PathVariable StatusReclamation status) {
        return reclamationService.getListReclamationsByStatusSorted(status);
    }

}
