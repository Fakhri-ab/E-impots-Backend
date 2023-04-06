package tn.arabsoft.spring.controllers;


import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.arabsoft.spring.services.ReclamationService;
import tn.arabsoft.spring.services.EmailSenderService;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class PdfController {

    @Autowired
    private EmailSenderService service;
    private final ReclamationService reclamationService;


    public PdfController(ReclamationService reclamationService) {
        this.reclamationService = reclamationService;
    }



    @GetMapping("/downloadPdf")
    public ResponseEntity<InputStreamResource> generatePDF1 () throws IOException, DocumentException {
      //  response.setContentType("application/pdf");
      //  DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
      //  String currentDateTime = dateFormatter.format(new Date());

      //  String headerKey = "Content-Disposition";
      //  String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
      //  response.setHeader(headerKey, headerValue);

       // this.reclamationService.export(response);
        ByteArrayOutputStream baos = this.reclamationService.export();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=pdf_" + new Date().toString() + ".pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(new ByteArrayInputStream(baos.toByteArray())));
    }

}



