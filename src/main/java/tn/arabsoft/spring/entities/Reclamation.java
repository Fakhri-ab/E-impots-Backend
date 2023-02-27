package tn.arabsoft.spring.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reclamation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReclamation;

    private  String  description;

    @Enumerated(EnumType.STRING)
    private typeReclamation typeReclamation;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date DateOfReclam;

    @Enumerated(EnumType.STRING)
    private StatusReclamation Status;


    @ManyToOne
    private User user;



  

}
