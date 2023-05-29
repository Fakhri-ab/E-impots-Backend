package tn.arabsoft.spring.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DeclarationTF {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String fullNameProprietaire ;
    private String AdresseBien ;
    private String TypeBien ;
    private float SuperficieBien ;
    private float Valeurlocative ;
    private float MontantTaxeFonciere ;
    private String SituationFiscale ;
    @Temporal(TemporalType.DATE)
    //@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date DateOfDeclarationTF;
    @ManyToOne
    private User user;
}
