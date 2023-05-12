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
public class DeclarationTVA {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private float VentesSoumisesTVANormale ;
    private float VentesExonerees ;
    private float Exportations  ;
    private float AchatsAupresFournisseursSoumisTVANormale  ;
    private float AchatsAupresFournisseursEtrangers   ;
    private float ChargesDeductibles   ;
    private float TVACollectee   ;
    private float TVADeductible    ;
    private float TVANetteAPaye    ;
    private Date DateOfDeclarationTVA;

    private String fullName ;
    @ManyToOne
    private User user;


}