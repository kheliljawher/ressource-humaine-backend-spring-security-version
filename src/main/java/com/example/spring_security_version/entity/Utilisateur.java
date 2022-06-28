package com.example.spring_security_version.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String nom;

    private String prenom;

    private String login;

    private String password;

    private String cin;

    private String telephone;

    private String email;

    private String adresse;

    private String code_pin;

    private String code_badge;

    private String poste;

    private String date_Embauche;

    private String date_Naissance;

    private String role;

    private String image;

    private String confirmPassword;

    private String status;

    private String sexs;

    private String actif;

   // @OneToMany(mappedBy = "utilisateur")
    //@Getter( onMethod = @__(@JsonIgnore))
    //@JsonIgnore
    //private List<Departement> departements;

    @ManyToOne
    @JoinColumn(name = "id_departement")
    //@Getter( onMethod = @__(@JsonIgnore))
    //@JsonIgnore
    private Departement departement;

    @ManyToOne
    @JoinColumn(name = "id_planning")
    //@Getter( onMethod = @__(@JsonIgnore))
    //@JsonIgnore
    private Planning planning;

    @OneToMany(mappedBy = "utilisateur")
    @Getter( onMethod = @__(@JsonIgnore))
    //@JsonIgnore
    private List<Conge> conges;
}
