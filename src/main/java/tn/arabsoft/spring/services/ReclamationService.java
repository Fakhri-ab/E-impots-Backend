package tn.arabsoft.spring.services;


import com.lowagie.text.Document;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import tn.arabsoft.spring.entities.Reclamation;
import tn.arabsoft.spring.entities.StatusReclamation;
import tn.arabsoft.spring.entities.User;
import tn.arabsoft.spring.repositories.ReclamationRepository;
import tn.arabsoft.spring.repositories.UserRepositroy;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service
public class ReclamationService {
    @Autowired
    ReclamationRepository reclamationRepository ;

    @Autowired
    UserRepositroy userRepositroy ;

    @Autowired
    private EmailSenderService servicemail;

    public  List<Reclamation> retrieveAllReclamations() {
        List<Reclamation> listReclamations = reclamationRepository.findAll();
        return listReclamations ;
    }

  public Page<Reclamation> getAllReclamations(PageRequest pr, String recherche){
      if (recherche.equals(""))
          return (Page<Reclamation>) reclamationRepository.findAll(pr);

      List<Reclamation> reclamations= reclamationRepository.findAll().stream()
              .filter(reclamation ->  reclamation.getStatus().name().equals(recherche) || reclamation.getDescription().contains(recherche))
              .collect(Collectors.toList());

      System.out.println("reclamations" +reclamations );

      int start = (int) pr.getOffset();
      int end = (int) ((start + pr.getPageSize()) > reclamations.size() ? reclamations.size()
              : (start + pr.getPageSize()));
      Page<Reclamation> allReclamationpage = new PageImpl<>(reclamations.subList(start, end),pr,reclamations.size());
      System.out.println("allReclamationpage " +allReclamationpage );
      return allReclamationpage;
    }


    public Reclamation addReclamation(Reclamation r, int idUser ) throws IOException, MessagingException {
        Reclamation re ;
        User u = userRepositroy.findById(idUser).orElse(null);
    //  Produit p =IproduitRepository.findById(idProduit).orElse(null);
        r.setUser(u);
   //   r.setProduitReclamation(p);
        r.setStatus(StatusReclamation.Nouvelle);
        r.setDateOfReclam(new Date());
        re= reclamationRepository.save(r);
      //  servicemail.sendEmailWithAttachment(u.getEmail(),"bonjour Monsieur","E-impots reclamation service",export());
        log.info("Reclamation ajouter " +re);
        return re ;

    }

    public void deleteReclamationById(int id) {
        reclamationRepository.deleteById(id);
        log.info("Reclamation Suprimer ");
    }

    public Reclamation retrieveReclamationById(int id) {

        Reclamation reclamation = reclamationRepository.findById(id).orElse(null);
        return reclamation;
    }

    public Reclamation updateReclamation(Reclamation r ) {
        Optional<Reclamation> optionalInfo = reclamationRepository.findById(r.getIdReclamation());
        Reclamation existingReclamation = null;
        if (optionalInfo.isPresent()) {
            existingReclamation = optionalInfo.get();
            existingReclamation.setDescription(r.getDescription());
            existingReclamation.setTypeReclamation(r.getTypeReclamation());
            existingReclamation.setDateOfReclam(r.getDateOfReclam());
            existingReclamation.setStatus(r.getStatus());
            return reclamationRepository.save(existingReclamation);
        } else {
            // handle error here
        }
        return existingReclamation;
    }

    public int getTotalReclamation() {
        int nbReclamation;
        nbReclamation = reclamationRepository.getTotalReclamation();
        return nbReclamation;
    }

    public int getNbNewReclamation() {
        int NbNewReclamation;
        LocalDate d = LocalDate.now();
        Date dateDuJour = java.sql.Date.valueOf(d);
        NbNewReclamation = reclamationRepository.getNbNewReclamation();
        return NbNewReclamation;
    }

    public int getNbPendingReclamation() {
        int NbPendingReclamation;
        NbPendingReclamation = reclamationRepository.getNbPendingReclamation();
        return NbPendingReclamation;
    }


    public int getNbTreatedReclamation() {
        int NbTreatedReclamation;
        NbTreatedReclamation = reclamationRepository.getNbTreatedReclamation();
        return NbTreatedReclamation;
    }

    public List<Reclamation> getallReclamationsByUserId(int UserId) {
        List <Reclamation> l =  reclamationRepository.getallReclamationsByUserId(UserId);
        return  l ;
    }

    public List<Reclamation> getListReclamationsByStatusSorted(StatusReclamation status) {
        return reclamationRepository.getListReclamationsByStatusSorted(status);
    }



    public void export1(HttpServletResponse response  ) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        Paragraph paragraph = new Paragraph("****Service De Reclamation****", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        paragraph.setSpacingAfter(10);


        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);

        Paragraph paragraph2 = new Paragraph("Cher client,  " + " Cher client," +
                "Nous avons bien reçu votre réclamation " +
                "Soyez assuré que nous prenons très au sérieux vos commentaires" +
                "Nous avons d'ores et déjà transmis votre réclamation à notre équipe compétente " +
                "et nous allons tout mettre en œuvre pour régler cette situation au plus vite " +
                "Nous vous remercions de votre patience et de votre compréhension et " +
                " nous nous engageons à vous tenir informé de l'évolution de la situation. " +
                " S'il vous plaît, n'hésitez pas à nous contacter si vous avez des questions supplémentaires " +
                "Cordialement", fontParagraph);
        paragraph2.setAlignment(Paragraph.ALIGN_LEFT);

       // Date d = new Date() ;
         ;
       // String filename1 = "./src/main/resources/woman.png";
       // Image image2 = Image.getInstance(filename1);
       // image2.setAlignment(Image.RIGHT);
      //  image2.scaleToFit(100, 100);


        String filename = "./src/main/resources/signature.png";
        Image image = Image.getInstance(filename);
        image.setAlignment(Image.LEFT);
        image.scaleToFit(100,100);

        document.add(paragraph);
        document.add(paragraph2);
        document.add(image);
        document.addCreationDate() ;
       // document.add(image2);
        document.close();
    }

    public ByteArrayOutputStream export() throws DocumentException, IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, baos);
        document.open();
        // Ajoutez ici le contenu du PDF
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        Paragraph paragraph = new Paragraph("****Service De Reclamation****", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        paragraph.setSpacingAfter(10);


        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);

        Paragraph paragraph2 = new Paragraph("Cher client,  " + " Cher client," +
                "Nous avons bien reçu votre réclamation " +
                "Soyez assuré que nous prenons très au sérieux vos commentaires" +
                "Nous avons d'ores et déjà transmis votre réclamation à notre équipe compétente " +
                "et nous allons tout mettre en œuvre pour régler cette situation au plus vite " +
                "Nous vous remercions de votre patience et de votre compréhension et " +
                " nous nous engageons à vous tenir informé de l'évolution de la situation. " +
                " S'il vous plaît, n'hésitez pas à nous contacter si vous avez des questions supplémentaires " +
                "Cordialement", fontParagraph);
        paragraph2.setAlignment(Paragraph.ALIGN_LEFT);

        // Date d = new Date() ;
        ;
        // String filename1 = "./src/main/resources/woman.png";
        // Image image2 = Image.getInstance(filename1);
        // image2.setAlignment(Image.RIGHT);
        //  image2.scaleToFit(100, 100);




        document.add(paragraph);
        document.add(paragraph2);
        document.addCreationDate() ;

        document.close();
        return baos;
    }
}
