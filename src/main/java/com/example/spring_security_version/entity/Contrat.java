package com.example.spring_security_version.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type_contrat;

    private String date_debut;

    private String date_fin;
//Double
    private String salaire;

    @JoinColumn(name = "id_responsable_rh")
    @ManyToOne
    private ResponsableRH responsable_rh;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur utilisateur;

}
