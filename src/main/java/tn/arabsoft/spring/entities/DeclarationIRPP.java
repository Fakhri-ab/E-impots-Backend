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
public class DeclarationIRPP {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private float salaires ;
    private float revenuesFonciers ;
    private float RevenusCapitauxMobiliers ;
    private float BeneficesIndustrielsCommerciaux ;
    private float BeneficesNnonCommerciaux ;
    private float PCVMI ;
    private String fullName ;
    private String SituationFiscale ;
    private float Montanpayer ;
    @Temporal(TemporalType.DATE)
    //  @JsonFormat(pattern="yyyy-MM-dd")
    //  @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date DateOfDeclarationIRPP;
    @ManyToOne
    private User user;

}
