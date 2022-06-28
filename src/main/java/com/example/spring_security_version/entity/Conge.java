package com.example.spring_security_version.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Conge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type_conge;

    private String date_debut;

    private String date_fin;

    private String status;

    private Long periode;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur utilisateur;

    //@ManyToOne
    //@Getter( onMethod = @__(@JsonIgnore))
    //@JsonIgnore
    //@JoinColumn(name = "id_employe")
    //private Employe employe;

}
