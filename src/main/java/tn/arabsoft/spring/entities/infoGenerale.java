package tn.arabsoft.spring.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class infoGenerale {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String NIF;
    private String nomRaisonsociale ;
    private String adresseGeographique ;
    private String adressePostale ;
    private String ville ;
    private Long Telephone ;
    private String Email ;
    @Enumerated(EnumType.STRING)
    private Activites activites ;
    @OneToOne
    private User user ;


}
