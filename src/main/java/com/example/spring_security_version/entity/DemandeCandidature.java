package com.example.spring_security_version.entity;

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
    private Candidature candidature;

}