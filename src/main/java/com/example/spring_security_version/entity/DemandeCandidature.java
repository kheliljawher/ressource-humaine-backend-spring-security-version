package com.example.spring_security_version.entity;

import com.example.spring_security_version.serializers.CandidatureSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DemandeCandidature implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String etat;

    @JoinColumn(name = "id_candidat")
    @ManyToOne
    private Candidat candidat;

    @JoinColumn(name = "id_candidature")
    @ManyToOne
    @JsonSerialize(using = CandidatureSerializer.class)
    private Candidature candidature;

   // @JoinColumn(name = "id_candidature_detail")
   // @ManyToOne
    //private Candidature candidatureDetail;

}
