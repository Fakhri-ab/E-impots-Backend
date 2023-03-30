package tn.arabsoft.spring.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class demandeInscription {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String userName;
    private String userFName;
    private String userLName;
    private String email;
    private String Password;
    private String roleName ;
    @Enumerated(EnumType.STRING)
    private StatusDemandeInscription Status;
}
