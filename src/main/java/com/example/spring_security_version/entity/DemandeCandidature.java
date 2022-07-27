package com.example.spring_security_version.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DemandeCandidature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String etat;

    @JoinColumn(name = "id_candidat")
    @ManyToOne
    private Candidat candidat;

    @JoinColumn(name = "id_candidature")
    @ManyToOne
    @Getter( onMethod = @__(@JsonIgnore))
    @JsonIgnore
    private Candidature candidature;

   // @JoinColumn(name = "id_candidature_detail")
   // @ManyToOne
    //private Candidature candidatureDetail;

}
