package com.example.spring_security_version.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Candidature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String offre;

    private String titre;

    private String date_debut;

    private String date_fin;

    private String status;

    private Long applicant;

    private String type;

    private Long experience;

    private Double salaire;

    private String description;

    @JoinColumn(name = "id_candidat")
    @ManyToOne
    private Candidat candidat;

    @JoinColumn(name = "id_responsable_rh")
    @ManyToOne
    private ResponsableRH responsable_rh;

    @ManyToOne
    @JoinColumn(name = "id_departement")
    //@Getter( onMethod = @__(@JsonIgnore))
    //@JsonIgnore
    private Departement departement;

    @OneToMany(mappedBy = "candidature")

    private List<DemandeCandidature> demandeCandidatures;

}
